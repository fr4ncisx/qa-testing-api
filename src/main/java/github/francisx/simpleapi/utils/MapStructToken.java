package github.francisx.simpleapi.utils;

import github.francisx.simpleapi.infrastructure.security.dto.response.AccessTokenResponse;
import github.francisx.simpleapi.infrastructure.security.model.AccessToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructToken extends IMapStruct<AccessToken, AccessTokenResponse> {

}
