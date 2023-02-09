package org.png.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.png.dto.CitizenDTO;
import org.png.entity.Citizen;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CitizenMapper {

    @Mapping(target = "firstName", expression = "java(citizenDTO.getFullName() !=null? citizenDTO.getFullName().substring(0,citizenDTO.getFullName().indexOf(' ')):null)")
    @Mapping(target = "lastName", expression = "java(citizenDTO.getFullName().substring(citizenDTO.getFullName().indexOf(' ')))")
    @Mapping(target = "address", expression = "java(citizenDTO.getAddress()+\"-\"+citizenDTO.getPinCode())")
    @Mapping(target = "id", ignore = true)
    Citizen toDAO(CitizenDTO citizenDTO);

    @Mapping(target = "fullName", expression = "java(citizen.getFirstName()+' '+citizen.getLastName())")
    @Mapping(target = "address", expression = "java(citizen.getAddress().substring(0,citizen.getAddress().indexOf('-')))")
    @Mapping(target = "pinCode", expression = "java(citizen.getAddress().substring(citizen.getAddress().indexOf('-')))")
    CitizenDTO toDTO(Citizen citizen);

    @Mapping(target ="id",ignore = true)
    void merge(@MappingTarget Citizen target, Citizen source);
}
