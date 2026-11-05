package com.cda.Product.service;

import com.cda.Product.dto.ProductRequestDto;
import com.cda.Product.dto.ProductResponseDto;
import com.cda.Product.exception.ResourceNotFoundException;
import com.cda.Product.model.Product;
import com.cda.Product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
            Optional<Product> productoObtenido  = repository.findById(id);
            ProductResponseDto result;

            try {
                 result = getProductoDto(productoObtenido.get());
            }
            catch (NoSuchElementException e){
                String message = "Producto no encontrado, id: " + id;
                throw new ResourceNotFoundException(message,404);
            }

            return result;
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
        product.setCategory(dto.category());
        product.setStock(dto.stock());
        //products.add(product);
        //nextId++;
        return getProductoDto(repository.save(product));
    }

    public  List<ProductResponseDto> getByName(String name, String category){
        List<ProductResponseDto> result = new ArrayList<>();
        //Visto en clase
        //repository.findByNameContainingIgnoreCaseAndCategory(name, category).forEach(p -> result.add(getProductoDto(p)));
        //repository.findProductsConJPQL(name, category).forEach(p -> result.add(getProductoDto(p)));
        repository.findProductsConSQL(name, category).forEach(p -> result.add(getProductoDto(p)));
        return result;
    }

    public List<ProductResponseDto> searchByName(String name) {
        List<ProductResponseDto> result = new ArrayList<>();
        repository.findByNameContainingIgnoreCase(name).forEach(p -> result.add(getProductoDto(p)));
        return result;
    }

    public List<ProductResponseDto> findByCategory(String category) {
        List<ProductResponseDto> result = new ArrayList<>();
        repository.findByCategory(category).forEach(p -> result.add(getProductoDto(p)));
        return result;
    }

    public List<ProductResponseDto> findByPriceRange(BigDecimal min, BigDecimal max) {
        List<ProductResponseDto> result = new ArrayList<>();
        repository.findByPriceBetween(min, max).forEach(p -> result.add(getProductoDto(p)));
        return result;
    }

    public List<ProductResponseDto> getTopByCategory(String category) {
        List<ProductResponseDto> result = new ArrayList<>();
        repository.findByCategoryOrderByPriceDesc(category).forEach(p -> result.add(getProductoDto(p)));
        return result;
    }

    public Long countByCategory(String category) {
        return repository.countByCategory(category);
    }

    private ProductResponseDto getProductoDto(Product producto) {
        return new ProductResponseDto(producto.getId(), producto.getName(), producto.getDescription(), producto.getPrice(), producto.getCategory(), producto.getStock());
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
