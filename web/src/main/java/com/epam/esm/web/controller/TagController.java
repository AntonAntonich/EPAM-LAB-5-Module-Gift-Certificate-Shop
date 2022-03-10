package com.epam.esm.web.controller;

import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.dto.CustomTagDto;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.localization.MessageKey;
import com.epam.esm.util.localization.MessageManager;
import com.epam.esm.util.service.TagService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomTagDto>> getAllTags
            (@RequestParam(defaultValue = "1") int page) throws CustomServiceException {
        List<CustomTagDto> tagList = service.getAllTags(page);
        for(CustomTagDto customTagDto : tagList) {
            setLinks(customTagDto);
            Link link = linkTo(TagController.class).slash(customTagDto.getId()).withSelfRel();
            customTagDto.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tagList);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<CustomTagDto> getTagById(@PathVariable int id) throws CustomServiceException {
        CustomTagDto customTagDto = service.getTagById(id);
        setLinks(customTagDto);
        Link link = linkTo(TagController.class).slash(customTagDto.getId()).withSelfRel();
        customTagDto.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(service.getTagById(id));
    }

    @PostMapping("/admin")
    public ResponseEntity<CustomTagWithoutListDto> saveTag(@RequestBody CustomTagWithoutListDto tag) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.addNewTag(tag));
    }

    @PutMapping("/admin")
    public ResponseEntity<CustomTagWithoutListDto> updateTag(@RequestBody CustomTagWithoutListDto tag) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTag(tag));
    }

    @DeleteMapping("/admin/{id}")
    public String deleteTagById(@PathVariable int id) throws CustomServiceException {
        return service.deleteTag(id) ? MessageManager.toLocale(MessageKey.DELETED_SUCCESSFUL) + id
                : MessageManager.toLocale(MessageKey.OPERATION_CRASHED);
    }

    private void setLinks(CustomTagDto customTagDto) {
        List<GiftWithoutListDto> giftList = customTagDto.getGifts();
        if(giftList != null) {
            for(GiftWithoutListDto gift : giftList) {
                Link link = linkTo(TagController.class).slash(gift.getId()).withSelfRel();
                gift.add(link);
            }
        }
    }
}
