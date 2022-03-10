package com.epam.esm.web.controller;

import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.GiftDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.localization.MessageKey;
import com.epam.esm.util.localization.MessageManager;
import com.epam.esm.util.service.GiftService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private GiftService service;

    public GiftController(GiftService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GiftDto>> getAllGifts(@RequestParam(defaultValue = "1") int page) throws CustomServiceException {
        List<GiftDto> gifts = service.getAllGifts(page);
        for(GiftDto giftDto : gifts) {
            setLinks(giftDto);
            Link link = linkTo(GiftController.class).slash(giftDto.getId()).withSelfRel();
            giftDto.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(gifts);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<GiftDto> getGiftById(@PathVariable int id) throws CustomServiceException {
        GiftDto giftDto = service.getGiftById(id);
        setLinks(giftDto);
        Link link = linkTo(GiftController.class).slash(giftDto.getId()).withSelfRel();
        giftDto.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(giftDto);
    }

    @PostMapping("/admin")
    public ResponseEntity<GiftWithoutListDto> saveGift(@RequestBody GiftWithoutListDto gift) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.addNewGift(gift));
    }

    @PutMapping("/admin")
    public ResponseEntity<GiftWithoutListDto> updateGift(@RequestBody GiftWithoutListDto gift) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateGift(gift));
    }

    @PutMapping("/admin/id/{id}/price/{price}")
    public ResponseEntity<GiftWithoutListDto> updateGiftPrice(@PathVariable int id,
                                                              @PathVariable String price) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateGiftPrice(id, price));
    }

    @PutMapping("/admin/id/{id}/duration/{duration}")
    public ResponseEntity<GiftWithoutListDto> updateGiftDuration(@PathVariable int id,
                                                                 @PathVariable int duration) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateGiftDuration(id, duration));
    }


    @DeleteMapping("/admin/{id}")
    public String deleteGiftById(@PathVariable int id) throws CustomServiceException {
        return service.deleteGift(id) ? MessageManager.toLocale(MessageKey.DELETED_SUCCESSFUL) + id
                : MessageManager.toLocale(MessageKey.OPERATION_CRASHED);
    }

    @GetMapping("/user/search-by-several-tags/tagOne/{tagOne}/tagTwo/{tagTwo}")
    public ResponseEntity<List<GiftWithoutListDto>> getGiftBySeveralTags(@PathVariable String tagOne,
                                                                         @PathVariable String tagTwo) throws CustomServiceException {
        List<GiftWithoutListDto> gifts = service.getGiftBySeveralTags(tagOne, tagTwo);
        for(GiftWithoutListDto gift : gifts) {
            Link link = linkTo(GiftController.class).slash(gift.getId()).withSelfRel();
            gift.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(gifts);
    }

    private void setLinks(GiftDto giftDto) throws CustomServiceException {
        List<CustomTagWithoutListDto> tags = giftDto.getTags();
        if (tags != null) {
            for (CustomTagWithoutListDto tag : tags) {
                Link selfLink = linkTo(methodOn(TagController.class).getTagById(tag.getId())).withSelfRel();
                tag.add(selfLink);
            }
        }
    }
}
