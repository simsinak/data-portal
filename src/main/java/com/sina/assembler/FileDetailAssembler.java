package com.sina.assembler;

import com.sina.config.FetchProperties;
import com.sina.controller.FileController;
import com.sina.domain.Record;
import com.sina.dto.FileDetailResponseDto;
import com.sina.entity.FileDetail;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Sina Askarnejad
 */
@Component
public class FileDetailAssembler extends RepresentationModelAssemblerSupport<FileDetail, FileDetailResponseDto> {

    private final int pageSize;

    public FileDetailAssembler(FetchProperties fetchProperties) {
        super(FileDetailAssembler.class, FileDetailResponseDto.class);
        pageSize = fetchProperties.getPageSize();
    }

    @Override
    @SneakyThrows
    public FileDetailResponseDto toModel(FileDetail entity) {
        FileDetailResponseDto responseDto = FileDetailResponseDto.builder()
                .id(entity.getId())
                .source(entity.getSource())
                .codeListCode(entity.getCodeListCode())
                .code(entity.getCode())
                .displayValue(entity.getDisplayValue())
                .longDescription(entity.getLongDescription())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .sortingPriority(entity.getSortingPriority())
                .build();
        Link link = linkTo(methodOn(FileController.class).fetchByCode(responseDto.getCode())).withSelfRel();
        responseDto.add(link);
        return responseDto;
    }

    @Override
    @SneakyThrows
    public CollectionModel<FileDetailResponseDto> toCollectionModel(Iterable<? extends FileDetail> entities) {
        CollectionModel<FileDetailResponseDto> collectionModel = super.toCollectionModel(entities);
        Link link = linkTo(methodOn(FileController.class).getAllDetails(0, pageSize)).withSelfRel();
        collectionModel.add(link);
        return collectionModel;
    }

    private FileDetail convertRecordToEntity(Record record) {
        FileDetail fileDetail = new FileDetail();
        fileDetail.setSource(record.getSource());
        fileDetail.setCodeListCode(record.getCodeListCode());
        fileDetail.setCode(record.getCode());
        fileDetail.setDisplayValue(record.getDisplayValue());
        fileDetail.setLongDescription(record.getLongDescription());
        fileDetail.setFromDate(record.getFromDate());
        fileDetail.setToDate(record.getToDate());
        fileDetail.setSortingPriority(record.getSortingPriority());
        return fileDetail;
    }

    public List<FileDetail> convertRecordsToEntity(List<Record> records) {
        return records.stream().map(this::convertRecordToEntity).collect(Collectors.toList());
    }
}
