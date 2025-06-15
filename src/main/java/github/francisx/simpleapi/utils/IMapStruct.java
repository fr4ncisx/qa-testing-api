package github.francisx.simpleapi.utils;

public interface IMapStruct<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
