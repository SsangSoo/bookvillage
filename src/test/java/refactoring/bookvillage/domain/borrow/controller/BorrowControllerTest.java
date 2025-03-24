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
import refactoring.bookvillage.domain.borrow.controller.request.CreateBorrowRequest;
import refactoring.bookvillage.domain.borrow.controller.request.UpdateBorrowRequest;
import refactoring.bookvillage.domain.borrow.service.command.BorrowCommandService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BorrowController.class)
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BorrowCommandService borrowService;


    @Test
    @DisplayName("대여 게시글 생성 요청 데이터에 게시글 제목, 본문, 책 제목에는 null값을 허용할 수 없다.")
    void createBorrowRequestDataValidationTest() throws Exception {
        //given
        CreateBorrowRequest request = new CreateBorrowRequest(null, "본문", "책 제목", "작가", "출판사", "썸네일 주소");

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
    @DisplayName("게시글 생성 요청을 할 수 있다.")
    void createBorrowRequestTest() throws Exception {
        //given
        CreateBorrowRequest request = new CreateBorrowRequest("대여 게시글 제목", "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("대여 게시글 수정 요청 데이터에 게시글 제목, 본문, 책 제목에는 null값을 허용할 수 없다.")
    void updateBorrowRequestDataValidationTest() throws Exception {
        //given
        UpdateBorrowRequest request = new UpdateBorrowRequest(null, "본문", "책 제목", "작가", "출판사", "썸네일 주소");

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
    @DisplayName("대여 게시글 수정을 요청할 수 있다.")
    void updateBorrowRequestTest() throws Exception {
        //given
        UpdateBorrowRequest request = new UpdateBorrowRequest("대여 게시글 제목", "본문", "책 제목", "작가", "출판사", "썸네일 주소");

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/borrow/" + 1L)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("대여 게시글 삭제를 요청할 수 있다.")
    void deleteBorrowRequestTest() throws Exception {
        //given //when //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/borrow/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("대여 게시글 조회를 할 수 있다.")
    void getBorrowRequestTest() throws Exception {
        //given //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/borrow/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("대여 게시글 목록 조회를 할 수 있다.")
    void getBorrowListRequestTest() throws Exception {
        //given //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/borrow")
                        .queryParam("keyword", "키워드")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }





}