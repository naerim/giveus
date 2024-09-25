// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

contract FundUsage {

    struct Expense {
        string item; // 항목
        string date; // 일자
        string name; // 명칭
        uint times; // 횟수
        uint amount; // 금액 표시
    }

    Expense[] public expenses;
    Expense[][] public expensesList;

    function addExpense(Expense[] memory newExpenses) public {
        for (uint i = 0; i < newExpenses.length; i++) {
            expenses.push(newExpenses[i]);
        }
        expensesList.push(expenses);
        delete expenses; // expenses 배열을 비웁니다.
    }

    function getExpenses() public view returns (Expense[] memory) {
        return expenses;
    }

    function getExpenseList() public view returns (Expense[][] memory) {
        return expensesList;
    }
}