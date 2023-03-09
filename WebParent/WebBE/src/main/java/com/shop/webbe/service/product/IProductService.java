package com.shop.webbe.service.product;


import com.shop.webcommon.dto.ProductDto;
import com.shop.webbe.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<ProductDto> {
    List<ProductDto> findAllByName(String name);
}
