package com.cda.Product.service;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;
import com.cda.Product.model.Product;
import com.cda.Product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceI {

        ProductRepository repository;

        public ProductService(ProductRepository repository) {
            this.repository = repository;
        }

        private List<Product> productos=new ArrayList<>();

        private Long id = 0L;

        public List<ProductResponseDto> obtenerProductos() {
            List<ProductResponseDto> lista = new ArrayList<>();
            productos.forEach(product -> {
                lista.add(getProductoDto(product));
            });

            return lista;
        }

        public ProductResponseDto getById(Long id) {
            if(id==null){
                //throw new ProductIdNullException("el id no puede ser null");
            }
           return getProductoDto(repository.findById(id).get());
    }



/*
    // Simular una base de datos
    private final List<Product> products = new ArrayList<>();
    // Contador para los IDs. Empieza en 1.
    private Long nextId = 1L;


    public ProductResponseDto actualizarStock(Long id, Integer nuevoStock) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setStock(nuevoStock);
                return getProductoDto(p);
            }
        }
        return null;
    }
*/

    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = new Product();
        //product.setId(nextId);
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        //products.add(product);
        //nextId++;
        return getProductoDto(repository.save(product));
    }

    private ProductResponseDto getProductoDto(Product producto) {
        return new ProductResponseDto(producto.getId(), producto.getName(), producto.getDescription(), producto.getPrice(), producto.getStock());
    }
/*
    public List<ProductResponseDto> getAll() {
       List<ProductResponseDto> responseList = new ArrayList<>();
       for (Product p : products) {
        ProductResponseDto dto = toResponseDto(p);
        responseList.add(dto);
        }
        return responseList;
    }

    public ProductResponseDto getById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return toResponseDto(p);
            }
        }
        return null;
    }
*/

/*
    private ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
*/
}
