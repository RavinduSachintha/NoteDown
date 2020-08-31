package com.rs.notedown.utils;

import org.modelmapper.ModelMapper;

@SuppressWarnings({"UnusedDeclaration"})
public class ModelMap {
    public static final ModelMapper modelMapper = new ModelMapper();

    public static <T> Object convert(Object obj, Class<T> cls) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(obj, cls);
    }
}
