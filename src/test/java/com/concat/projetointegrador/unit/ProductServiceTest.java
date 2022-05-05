package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.ProductRepository;
import com.concat.projetointegrador.service.ProductService;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService service;
    private final ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);
    private final SellerService sellerServiceMock = Mockito.mock(SellerService.class);

    @BeforeEach
    public void init() {
        service = new ProductService(productRepositoryMock, sellerServiceMock);
    }

    private Product mockProduct() {
        return Product.builder()
                .id(1L)
                .price(BigDecimal.valueOf(10))
                .category(Category.CONGELADOS)
                .name("frango")
                .volume(1)
                .seller(Seller.builder().id(1L).name("Jonas").lastName("Viana").build())
                .build();
    }

    private List<Product> mockListProduct() {
        return Arrays.asList(Product.builder()
                        .name("carne")
                        .volume(1)
                        .category(Category.FRESCOS)
                        .price(BigDecimal.valueOf(20))
                        .build(),
                Product.builder()
                        .name("alface")
                        .volume(1)
                        .category(Category.FRESCOS)
                        .price(BigDecimal.valueOf(3))
                        .build());
    }

    @Test
    void shouldReturnGetProductDTOById() {
        ProductDTO productDTO;
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct()));
        productDTO = ProductDTO.convertToProductDTO(service.findById(Mockito.anyLong()));

        assertEquals("frango", productDTO.getName());
    }

    @Test
    void shouldReturnGetProductDTOByIdNotFound() {
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findById(Mockito.anyLong()));
        assertEquals("O produto não foi encontrado!!", exception.getMessage());
    }

    @Test
    void shouldReturnGetListProductDTO() {
        List<ProductDTO> listProductDTO;
        Mockito.when(productRepositoryMock.findAll()).thenReturn(mockListProduct());
        listProductDTO = service.findAll();
        assertEquals("carne", listProductDTO.get(0).getName());
    }

    @Test
    void shouldReturnGetListProductDTONotFound() {
        Mockito.when(productRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findAll());
        assertEquals("Não existem produtos cadastrados.", exception.getMessage());
    }

    @Test
    void shouldCreateProduct() {
        ProductDTO productDTO;
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(productRepositoryMock.save(Mockito.any())).thenReturn(mockProduct());
        productDTO = service.create(mockProduct());
        assertEquals("frango", productDTO.getName());
    }

    @Test
    void shouldReturnProductByCategory() {
        List<ProductDTO> listProductDTO;
        Mockito.when(productRepositoryMock.findByCategory(Mockito.any())).thenReturn(mockListProduct());
        listProductDTO = service.findByCategory(Category.CONGELADOS);
        assertEquals("carne", listProductDTO.get(0).getName());
    }

    @Test
    void shouldReturnProductByCategoryNotFound() {
        Mockito.when(productRepositoryMock.findByCategory(Mockito.any())).thenReturn(new ArrayList<>());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findByCategory(Category.CONGELADOS));
        assertEquals("Não existem produtos nesta categoria!", exception.getMessage());
    }

}
