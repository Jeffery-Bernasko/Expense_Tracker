package org.example.expense_tracker.controller;
import org.example.expense_tracker.dto.ExpenseRequestDTO;
import org.example.expense_tracker.dto.ExpenseResponseDTO;
import org.example.expense_tracker.mapper.ExpenseMapper;
import org.example.expense_tracker.model.Expense;
import org.example.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseService.getAll();
    }


    @PostMapping
    public ExpenseResponseDTO addExpense(@RequestBody ExpenseRequestDTO dto){
        // return ExpenseMapper.toDTO(saved);
        return expenseService.createExpense(dto);
    }



    @GetMapping("/{id}")
    public ExpenseResponseDTO getExpenseById1(@PathVariable Long id){
        Expense expense = expenseService.getById(id);
        return ExpenseMapper.toDTO(expense);
    }


    @PutMapping("/{id}")
    public ExpenseResponseDTO updateExpense(@PathVariable Long id, @RequestBody ExpenseRequestDTO requestDTO) {
        return expenseService.updateExpense(id,requestDTO);
    }


    @GetMapping("/filter/category")
    public List<Expense> filterByCategory(@RequestParam String category) {
        return expenseService.filterByCategory(category);
    }

    @GetMapping("/filter/date")
    public List<Expense> filterByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return expenseService.filterByDateRange(start, end);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteById(id);
    }


}
