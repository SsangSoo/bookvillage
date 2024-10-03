package refactoring.bookvillage.domain.borrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.service.BorrowService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BorrowController.class)
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BorrowService borrowService;


    @Test
    @DisplayName("대여 게시글 생성 요청 데이터(게시글 제목, 본문, 책 제목)검증 테스트(Bad case)")
    void createBorrowRequestDataValidationTest() throws Exception {
        //given
        CreateBorrowRequestDto request = new CreateBorrowRequestDto(null, "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("대여 게시글 제목은 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty());

    }

    @Test
    @DisplayName("대여 게시글 생성 요청 테스트(Happy Case)")
    void createBorrowRequestTest() throws Exception {
        //given
        CreateBorrowRequestDto request = new CreateBorrowRequestDto("대여 게시글 제목", "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("대여 게시글 수정 요청 데이터(게시글 제목, 본문, 책 제목)검증 테스트(Bad case)")
    void updateBorrowRequestDataValidationTest() throws Exception {
        //given
        UpdateBorrowRequestDto request = new UpdateBorrowRequestDto(null, "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/borrow/" + 1L)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("대여 게시글 제목은 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("대여 게시글 수정 요청 테스트(Happy Case)")
    void updateBorrowRequestTest() throws Exception {
        //given
        UpdateBorrowRequestDto request = new UpdateBorrowRequestDto("대여 게시글 제목", "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/borrow/" + 1L)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }



}