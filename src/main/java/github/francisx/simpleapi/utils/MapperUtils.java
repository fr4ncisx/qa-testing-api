package github.francisx.simpleapi.utils;

import org.modelmapper.ModelMapper;

public final class MapperUtils {

    private MapperUtils(){}

    public static <T,D> D convertDTO(T source, Class<D> output){
        ModelMapper mp = new ModelMapper();
        return mp.map(source, output);
    }

    public static <T,D> void convertDTO(T source, D destination){
        ModelMapper mp = new ModelMapper();
        mp.map(source, destination);
    }
}
