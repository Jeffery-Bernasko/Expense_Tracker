package org.example.expense_tracker.service;


import org.example.expense_tracker.dto.ExpenseRequestDTO;
import org.example.expense_tracker.dto.ExpenseResponseDTO;
import org.example.expense_tracker.mapper.ExpenseMapper;
import org.example.expense_tracker.model.Expense;
import org.example.expense_tracker.model.User;
import org.example.expense_tracker.repository.ExpenseRepository;
import org.example.expense_tracker.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository){
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }
    //Get all expenses
    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    //Get expense by id
    public Expense getById(Long id){
        return expenseRepository.findById(id).orElseThrow(() ->new RuntimeException("Expense not found"));
    }

    // Delete Expense
    public void deleteById(Long id){
        expenseRepository.deleteById(id);
        System.out.println("Expense successfully deleted");
    }




    public ExpenseResponseDTO createExpense(ExpenseRequestDTO expenseRequestDTO) {

        Expense expense = new Expense();

        // Get Authenticated username and create an expense
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        expense.setTitle(expenseRequestDTO.getTitle());
        expense.setCategory(expenseRequestDTO.getCategory());
        expense.setAmount(expenseRequestDTO.getAmount());
        expense.setDescription(expenseRequestDTO.getDescription());
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.toDTO(savedExpense);
    }
    // Update Expense
    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseUpdate){
        Expense  expense = expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Expense not found"));
        expense.setDescription(expenseUpdate.getDescription());
        expense.setTitle(expenseUpdate.getTitle());
        expense.setAmount(expenseUpdate.getAmount());
        expense.setCategory(expenseUpdate.getCategory());
        expense.setDate(LocalDate.now());

        Expense savedExpense = expenseRepository.save(expense);
        return  ExpenseMapper.toDTO(savedExpense);
    }

    //Find Expense by Category
    public List<Expense> filterByCategory(String category){
        return expenseRepository.findByCategory(category);
    }

    //Find Expense by Date Range
    public List<Expense> filterByDateRange(LocalDate start, LocalDate end){
        return expenseRepository.filterByDateRange(start,end);
    }
}
