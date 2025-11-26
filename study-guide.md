# NU-DRINK Ordering System Documentation

## 📋 Table of Contents
1. [Overview](#overview)
2. [Program Requirements](#program-requirements)
3. [Code Structure](#code-structure)
4. [Recursion Explanation](#recursion-explanation)
5. [Validation Logic](#validation-logic)
6. [Sample Outputs](#sample-outputs)
7. [How to Run](#how-to-run)

---

## 🎯 Overview

The **NU-DRINK Ordering System** is a Java console application that allows users to order drinks from a menu. The program demonstrates the use of **recursion** for input validation and order processing.

### Key Features:
- Menu-driven drink ordering
- Recursive input validation
- Receipt generation with total calculation
- Error handling for invalid inputs

---

## 📌 Program Requirements

### Drink Menu:
| Code | Drink Name  | Price (PHP) |
|------|-------------|-------------|
| A    | Milktea     | 100         |
| B    | Coffee      | 105         |
| C    | Fruit Juice | 56          |

### Technical Requirements:
- ✅ Must use **recursion** (no loops like `for` or `while`)
- ✅ Accept both uppercase and lowercase input
- ✅ Validate order choices (only A, B, C allowed)
- ✅ Validate quantities (only positive integers)
- ✅ Display formatted receipt

---

## 🏗️ Code Structure

### 1. **Class: `NuDrinkOrderingSystem`**
Main class containing all program logic.

### 2. **Global Variable**
```java
static Scanner scanner = new Scanner(System.in);
```
- Shared scanner object for all input operations
- Declared as `static` so recursive methods can access it

### 3. **Methods**

#### `getOrder()` - Recursive Order Input
```java
public static char getOrder()
```
**Purpose:** Get and validate drink order choice from user

**How it works:**
1. Prompts user: `Enter Order[A/a]:`
2. Reads input as String
3. Validates input length (must be 1 character)
4. Converts to uppercase for comparison
5. Checks if input is A, B, or C
6. If invalid → prints "Invalid Input" and **recursively calls itself**
7. If valid → returns the character

**Recursion Point:**
```java
if (order != 'A' && order != 'B' && order != 'C') {
    System.out.println("Invalid Input");
    return getOrder(); // ← Recursion happens here
}
```

---

#### `getQuantity()` - Recursive Quantity Input
```java
public static int getQuantity()
```
**Purpose:** Get and validate quantity from user

**How it works:**
1. Prompts user: `Enter Quantity:`
2. Reads input as String
3. Attempts to parse as integer using `Integer.parseInt()`
4. Validates if quantity > 0
5. If invalid → prints "Enter Valid Quantity" and **recursively calls itself**
6. If valid → returns the quantity

**Validation Cases:**
- **Letters/symbols** → Caught by `NumberFormatException`
- **Zero** → Caught by `if (quantity <= 0)`
- **Negative numbers** → Caught by `if (quantity <= 0)`

**Recursion Point:**
```java
if (quantity <= 0) {
    System.out.println("Enter Valid Quantity");
    return getQuantity(); // ← Recursion happens here
}
```

---

#### `main()` - Program Entry Point
```java
public static void main(String[] args)
```
**Purpose:** Orchestrates the entire program flow

**Flow:**
1. Display menu
2. Call `getOrder()` to get valid drink choice
3. Call `getQuantity()` to get valid quantity
4. Determine drink name and price using `switch` statement
5. Calculate total price
6. Display formatted receipt

---

## 🔄 Recursion Explanation

### What is Recursion?
Recursion is when a function calls itself to solve a problem.

### Why Use Recursion Here?
The assignment specifically requires recursion instead of loops.

### How Recursion Works in This Program:

#### Example 1: Invalid Order Input
```
NU-DRINK MENU
[A] - Milktea
[B] - Coffee
[C] - Fruit Juice
Enter Order[A/a]: Z        ← User enters 'Z'
Invalid Input              ← Error message
Enter Order[A/a]: 5        ← User enters '5'
Invalid Input              ← Error message
Enter Order[A/a]: A        ← User enters 'A'
Enter Quantity:            ← Valid! Proceeds
```

**What happens behind the scenes:**
1. `getOrder()` is called (1st time)
2. User enters "Z" → invalid
3. `getOrder()` calls itself (2nd time) ← **Recursion**
4. User enters "5" → invalid
5. `getOrder()` calls itself (3rd time) ← **Recursion**
6. User enters "A" → valid
7. Returns 'A' to main

#### Example 2: Invalid Quantity Input
```
Enter Quantity: abc        ← User enters letters
Enter Valid Quantity       ← Error message
Enter Quantity: 0          ← User enters zero
Enter Valid Quantity       ← Error message
Enter Quantity: -5         ← User enters negative
Enter Valid Quantity       ← Error message
Enter Quantity: 2          ← User enters valid number
```

**What happens behind the scenes:**
1. `getQuantity()` is called (1st time)
2. User enters "abc" → `NumberFormatException`
3. `getQuantity()` calls itself (2nd time) ← **Recursion**
4. User enters "0" → quantity <= 0
5. `getQuantity()` calls itself (3rd time) ← **Recursion**
6. User enters "-5" → quantity <= 0
7. `getQuantity()` calls itself (4th time) ← **Recursion**
8. User enters "2" → valid
9. Returns 2 to main

---

## ✅ Validation Logic

### Order Validation Rules:
| Input Type | Valid? | Action |
|------------|--------|--------|
| A, a | ✅ Yes | Accept |
| B, b | ✅ Yes | Accept |
| C, c | ✅ Yes | Accept |
| Z, 5, @, empty | ❌ No | Show "Invalid Input" → Recursion |
| Multiple chars | ❌ No | Show "Invalid Input" → Recursion |

### Quantity Validation Rules:
| Input Type | Valid? | Action |
|------------|--------|--------|
| 1, 2, 100 | ✅ Yes | Accept |
| 0 | ❌ No | Show "Enter Valid Quantity" → Recursion |
| -1, -50 | ❌ No | Show "Enter Valid Quantity" → Recursion |
| abc, 1.5, @ | ❌ No | Show "Enter Valid Quantity" → Recursion |

---

## 📊 Sample Outputs

### Case 1: Perfect Input (No Errors)
```
NU-DRINK MENU
[A] - Milktea
[B] - Coffee
[C] - Fruit Juice
Enter Order[A/a]: A
Enter Quantity: 2

RECEIPT
2pc/pcs Milktea 100php each.
Total: 200
```

### Case 2: Invalid Order Input
```
NU-DRINK MENU
[A] - Milktea
[B] - Coffee
[C] - Fruit Juice
Enter Order[A/a]: Z
Invalid Input
Enter Order[A/a]: A
Enter Quantity: 4

RECEIPT
4pc/pcs Milktea 100php each.
Total: 400
```

### Case 3: Invalid Quantity Input
```
NU-DRINK MENU
[A] - Milktea
[B] - Coffee
[C] - Fruit Juice
Enter Order[A/a]: B
Enter Quantity: -2
Enter Valid Quantity
Enter Quantity: 0
Enter Valid Quantity
Enter Quantity: 4

RECEIPT
4pc/pcs Coffee 105php each.
Total: 420
```

### Case 4: Lowercase Input
```
NU-DRINK MENU
[A] - Milktea
[B] - Coffee
[C] - Fruit Juice
Enter Order[A/a]: c
Enter Quantity: 3

RECEIPT
3pc/pcs Fruit Juice 56php each.
Total: 168
```

---

## 🚀 How to Run

### Prerequisites:
- Java Development Kit (JDK) 8 or higher
- Command line / Terminal access

### Steps:

1. **Save the code**
   ```
   Save as: NuDrinkOrderingSystem.java
   ```

2. **Compile the program**
   ```bash
   javac NuDrinkOrderingSystem.java
   ```

3. **Run the program**
   ```bash
   java NuDrinkOrderingSystem
   ```

4. **Follow the prompts**
   - Enter your drink choice (A, B, or C)
   - Enter the quantity
   - View your receipt

---

## 🔍 Code Breakdown by Line

### Menu Display (Lines in main)
```java
System.out.println("NU-DRINK MENU");
System.out.println("[A] - Milktea");
System.out.println("[B] - Coffee");
System.out.println("[C] - Fruit Juice");
```
Displays the menu exactly as required.

### Order Processing
```java
char order = getOrder();
```
Calls recursive function to get valid order.

### Quantity Processing
```java
int quantity = getQuantity();
```
Calls recursive function to get valid quantity.

### Price Assignment
```java
switch(order) {
    case 'A':
        drinkName = "Milktea";
        price = 100;
        break;
    // ... other cases
}
```
Maps order choice to drink name and price.

### Receipt Generation
```java
int total = price * quantity;
System.out.println();
System.out.println("RECEIPT");
System.out.println(quantity + "pc/pcs " + drinkName + " " + price + "php each.");
System.out.println("Total: " + total);
```
Calculates and displays the receipt.

---

## 🎓 Learning Outcomes

By studying this code, you will understand:

1. **Recursion Fundamentals**
   - How a function can call itself
   - Base case vs recursive case
   - When recursion terminates

2. **Input Validation**
   - String to character conversion
   - Integer parsing with error handling
   - Exception handling (`try-catch`)

3. **Control Flow**
   - `switch` statements
   - Conditional logic with `if-else`
   - Method returns

4. **String Manipulation**
   - `trim()` - removes whitespace
   - `toUpperCase()` - converts to uppercase
   - `charAt()` - gets character at index

5. **User Input Handling**
   - Scanner class usage
   - `nextLine()` for reading input
   - Input buffer management

---

## 💡 Key Programming Concepts

### 1. **Base Case** (Recursion Termination)
```java
// In getOrder()
if (order is valid) {
    return order; // ← Base case: stops recursion
}
```

### 2. **Recursive Case** (Self-calling)
```java
// In getOrder()
if (order is invalid) {
    return getOrder(); // ← Recursive case: calls itself
}
```

### 3. **Exception Handling**
```java
try {
    int quantity = Integer.parseInt(input);
} catch (NumberFormatException e) {
    // Handle non-numeric input
}
```

---

## 🐛 Common Issues & Solutions

| Issue | Cause | Solution |
|-------|-------|----------|
| Program doesn't compile | Missing semicolon or brace | Check syntax carefully |
| Infinite recursion | No base case reached | Ensure validation logic is correct |
| Scanner errors | Not closing input properly | Use single Scanner instance |
| Wrong total | Calculation error | Verify: `total = price * quantity` |

---

## 📚 Additional Notes

- The program uses **static methods** because they're called from `main()`
- **Scanner** reads from `System.in` (keyboard input)
- **trim()** removes accidental spaces from user input
- **toUpperCase()** allows case-insensitive input
- The blank line before "RECEIPT" is intentional for formatting

---

## 🎯 Assignment Checklist

- ✅ Uses recursion (no loops)
- ✅ Validates order input (A, B, C only)
- ✅ Validates quantity (positive integers only)
- ✅ Handles invalid input with error messages
- ✅ Displays menu correctly
- ✅ Calculates total price
- ✅ Displays formatted receipt
- ✅ Accepts uppercase and lowercase

---

## 📝 Conclusion

This program demonstrates clean, beginner-friendly Java code that fulfills all the requirements of Activity 1 - Recursion. The recursive validation functions ensure that only valid input is processed, while the main method orchestrates the overall flow from menu display to receipt generation.
