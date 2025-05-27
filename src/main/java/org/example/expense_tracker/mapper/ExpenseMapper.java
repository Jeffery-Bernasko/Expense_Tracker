package org.example.expense_tracker.mapper;

import org.example.expense_tracker.dto.ExpenseRequestDTO;
import org.example.expense_tracker.dto.ExpenseResponseDTO;
import org.example.expense_tracker.model.Expense;
import org.example.expense_tracker.model.User;

import java.time.LocalDate;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseRequestDTO dto, User user){
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setCategory(dto.getCategory());
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setDate(LocalDate.now());
        expense.setUser(user);
        return expense;
    }

    public static ExpenseResponseDTO toDTO(Expense expense){
        ExpenseResponseDTO dto = new ExpenseResponseDTO();;

        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setCategory(expense.getCategory());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setDescription(expense.getDescription());
        return dto;
    }
}
