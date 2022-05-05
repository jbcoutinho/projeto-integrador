package com.concat.projetointegrador.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.service.*;
import com.concat.projetointegrador.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concat.projetointegrador.dto.InboundOrderDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;

@RestController
@RequestMapping("/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService orderService;

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ProductService productService;


    /**
     * search an inboud order list
     * @return returns a list of inbound orders
     */
    @GetMapping
    public Collection<InboundOrderDTO> findAllBy() {
        return orderService.findAll().stream().map(InboundOrderDTO::map).collect(Collectors.toList());
    }


    /**
     * search for an inbound order
     * @param id - Long that represents the unique identifier
     * @return InboundOrder - returns an object with type InboundOrder
     */
    @GetMapping("/{id}")
    public InboundOrder findAllById(@PathVariable Long id) {
        return orderService.findById(id);
    }


    /**
     * update one inbound order
     * @param id  - Long id that represents the inbound order on the database
     * @param dto - object with the data to update
     * @return returns the updated inbound order
     */
    @PutMapping("/{id}")
    public ResponseEntity<InboundOrderDTO> update(@PathVariable Long id, @RequestBody InboundOrderDTO dto) {
        List<BatchStock> list = dto.getBatchStock()
                .stream()
                .map(
                        e -> BatchStock.builder()
                                .category(batchStockService.findById(e.getId()).getCategory())
                                .currentQuantity(e.getCurrentQuantity())
                                .dueDate(e.getDueDate())
                                .initialQuantity(e.getInitialQuantity())
                                .initialTemperature(e.getInitialTemperature())
                                .currentTemperature(e.getInitialTemperature())
                                .manufacturingDate(e.getManufacturingDate())
                                .manufacturingTime(e.getManufacturingTime())
                                .id(e.getId())
                                .product(productService.findById(e.getProductId()))
                                .build()
                ).collect(Collectors.toList());
        InboundOrder inboundOrder = InboundOrderDTO.map(dto, sectorService.findById(dto.getSector().getSectorCode()), list);
        inboundOrder = orderService.update(id, inboundOrder);
        InboundOrderDTO map = InboundOrderDTO.map(inboundOrder);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    /**
     * create a new inbound order
     * @param dto - object with the data to registrate on database
     * @return returns the created inbound order
     */
    @PostMapping
    public ResponseEntity<InboundOrderDTO> create(@RequestBody InboundOrderDTO dto, UriComponentsBuilder uriBuilder) {
        Sector sector = sectorService.findById(dto.getSector().getSectorCode());

        warehouseService.findById(dto.getSector().getWarehouseCode());

        InboundOrder inboundOrder = InboundOrderDTO.map(dto, sector);

        List<BatchStock> list = dto.getBatchStock()
                .stream()
                .map(
                        e ->
                                BatchStock.builder()
                                        .inboundOrder(inboundOrder)
                                        .category(sector.getCategory())
                                        .currentQuantity(e.getInitialQuantity())
                                        .dueDate(e.getDueDate())
                                        .initialTemperature(e.getInitialTemperature())
                                        .currentTemperature(e.getInitialTemperature())
                                        .initialQuantity(e.getInitialQuantity())
                                        .manufacturingDate(e.getManufacturingDate())
                                        .manufacturingTime(e.getManufacturingTime())
                                        .product(productService.findById(e.getProductId()))
                                        .build()
                ).collect(Collectors.toList());

        inboundOrder.setBatchStock(list);

        InboundOrder order = orderService.create(inboundOrder);

        URI uri = uriBuilder.path("/fresh-products/inboundorder/{id}").buildAndExpand(order.getId()).toUri();

        InboundOrderDTO map = InboundOrderDTO.map(inboundOrder);
        return ResponseEntity.created(uri).body(map);
    }

}
