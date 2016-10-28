package com.bob.springboot.simple.service.impl;

import com.bob.springboot.search.constants.SearchConstants;
import com.bob.springboot.search.SearchComponent;
import com.bob.springboot.simple.mapper.CountryMapper;
import com.bob.springboot.simple.model.Country;
import com.bob.springboot.search.model.SearchField;
import com.bob.springboot.search.model.SearchRequest;
import com.bob.springboot.simple.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob Jiang on 2016/10/27.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> searchCountryList(Country country) {
        SearchComponent searchComponent = SearchComponent.INSTANCE;
        SearchRequest request = new SearchRequest();
        request.setIndexName(SearchConstants.INDEX_SPRINGBOOT_DEMO);
        request.setIndexType(new ArrayList<String>(){{
            add(SearchConstants.INDEX_TYPE_COUNTRY);
        }});
        request.setSearchField(new ArrayList<SearchField>(){{
            Object value = (country != null) ? country.getCountryname() : null;
            add(new SearchField("countryname", value, true));
        }});
//        request.setOrder(new ArrayList<ESOrder>(){{
//            add(new ESOrder("countryname"));
//        }});
        return searchComponent.searchList(request, Country.class);
//        return null;
    }

    @Override
    public Integer updateCountry(Country country) {
        return countryMapper.update(country);
    }

    @Override
    public Integer saveCountry(Country country) {
        return countryMapper.save(country);
    }

}