package kodluyoruz.project;

import kodluyoruz.project.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].name").exists())
                .andExpect(jsonPath("$[*].author").exists())
                .andExpect(jsonPath("$[*].page").exists());
    }

    @Test
    public void testGetBookById() throws Exception {
        String id = "1";

        mockMvc.perform(get("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.page").exists());
    }

    @Test
    public void testCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setName("Ana Karenina");
        newBook.setAuthor("Lev Tolstoy");
        newBook.setPage(583);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.page").exists());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book newBook = new Book();
        newBook.setId(3L);
        newBook.setName("Anna Karenina");
        newBook.setAuthor("Lev Tolstoy");
        newBook.setPage(583);

        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.page").exists());
    }

    @Test
    public void testDeleteBookById() throws Exception {
        String id = "1";

        mockMvc.perform(delete("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllBook() throws Exception {
        mockMvc.perform(delete("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
