package org.example.expense_tracker.repository;

import org.example.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);

    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :start AND :end")
    List<Expense> filterByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
