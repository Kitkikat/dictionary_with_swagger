package com.example.dictionary.service.Impl;

import com.example.dictionary.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.dictionary.model.Data;
import com.example.dictionary.repository.DataRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    DataRepository datarepository;

    @Override
    public Data saveData(Data data) {
        return datarepository.save(data);
    }

    @Override
    public Data findDataById(Long id) {
        return datarepository.findById(id).get();
    }

    @Override
    public List<Data> findAllData(){
        return datarepository.findAll();
    }

    @Override
    public Data updateData(Long id, Data data) {
        Data newData = datarepository.findById(id).get();

        newData.setDictionary_id(data.getDictionary_id());
        newData.setCode(data.getCode());
        newData.setValue(data.getValue());

        return datarepository.save(newData);
    }

    @Override
    public Data deleteData(Long id) {

        Data deletedData = datarepository.findById(id).get();

        datarepository.delete(datarepository.findById(id).get());

        return deletedData;
    }
}