async function addExpense(){

    const title = document.getElementById("title").value
    const amount = document.getElementById("amount").value
    const category = document.getElementById("category").value

    await fetch("http://localhost:8080/api/expenses",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            title,
            amount:parseFloat(amount),
            category
        })
    })

    loadExpenses()
}

async function loadExpenses(){

    const response = await fetch("http://localhost:8080/api/expenses")
    const expenses = await response.json()

    const totalResponse = await fetch("http://localhost:8080/api/expenses/total")
    const total = await totalResponse.text()

    document.getElementById("total").innerText = `₹${total}`

    const expenseList = document.getElementById("expenseList")

    expenseList.innerHTML = ""

    expenses.forEach(expense => {

        expenseList.innerHTML += `
            <div class="expense-item">
                <h3>${expense.title}</h3>
                <p>₹${expense.amount}</p>
                <p>${expense.category}</p>
            </div>
        `
    })
}

loadExpenses()