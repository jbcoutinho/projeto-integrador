package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.AddressCreateDTO;
import com.concat.projetointegrador.dto.AddressDTO;
import com.concat.projetointegrador.dto.BuyerDTO;
import com.concat.projetointegrador.model.Address;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.service.AddressService;
import com.concat.projetointegrador.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private AddressService addressService;

    /** create a new buyer
     * @param buyer - object buyer
     * @return a buyer DTO
     */
    @PostMapping
    public ResponseEntity<BuyerDTO> create(@RequestBody @Valid Buyer buyer) {
        BuyerDTO buyerDTO = BuyerDTO.convertToBuyerDTO(buyerService.create(buyer));
        return new ResponseEntity<>(buyerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/address/{id}")
    public ResponseEntity<BuyerDTO> createAddress(@PathVariable Long id, @RequestBody AddressCreateDTO addressDTO) {
        return new ResponseEntity<>(
                BuyerDTO
                    .convertToBuyerDTO(buyerService.addAddress(id, addressDTO)),
                HttpStatus.CREATED);
    }

}
