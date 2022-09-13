package com.cookingchallenges.api;

import com.cookingchallenges.domain.content.ContentFacade;
import com.cookingchallenges.domain.content.dto.ContentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("content")
class ContentController {

    private final ContentFacade contentFacade;

    @GetMapping("/{id}")
    ContentDTO getContentById(@PathVariable Long id) {
        return contentFacade.getContentById(id);
    }

    @GetMapping
    List<ContentDTO> getContentByName(@RequestParam String title) {
        return contentFacade.getContentByName(title);
    }

//    @PostMapping
//    void postContent(ContentDTO contentDTO) {} //post content -> contentDTO (or its substitute)

//-------------------------------------------------------------------------------------------

//    @GetMapping
//    List<ScreeningBasicInfo> getScreeningsByDate(@RequestParam(value = "from", required = false)
//                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                                                 LocalDateTime from,
//                                                 @RequestParam(value = "to", required = false)
//                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                                                 LocalDateTime to,
//                                                 @PageableDefault(sort = {"movie.title", "startTime"})
//                                                 Pageable pageable) {
//        return contentFacade.findScreeningsByDateTimeInterval(from, to, pageable);
//    }

}
