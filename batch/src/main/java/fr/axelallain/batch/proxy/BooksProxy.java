package fr.axelallain.batch.proxy;

import fr.axelallain.batch.dto.UpdateLoanDto;
import fr.axelallain.batch.dto.UpdateReservationDto;
import fr.axelallain.batch.model.Book;
import fr.axelallain.batch.model.Loan;
import fr.axelallain.batch.model.Reservation;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul")
@RibbonClient(name = "books")
public interface BooksProxy {

    @GetMapping("/books/loan")
    Iterable<Loan> findAll();

    @PutMapping("/books/loan")
    void loanAdd(@RequestBody UpdateLoanDto updateLoanDto);

    @GetMapping("/books/loan/{id}")
    Loan findById(@PathVariable Long id);

    @GetMapping("/books/reservations/{bookid}")
    List<Reservation> findByBookIdOrderByCreationDateDesc(@PathVariable Long bookid);

    @GetMapping("/books/reservations")
    List<Reservation> findAllReservations();

    @GetMapping(value = "/books/books")
    Iterable<Book> books();

    @PostMapping("/books/books")
    void booksAdd(Book book);

    @PostMapping("/books/reservations")
    void reservationsAdd(@RequestBody UpdateReservationDto updateReservationDto);
}
