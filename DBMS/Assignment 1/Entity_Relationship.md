# Database Management System - Assignments

## Session 1

### Assignment 1: Entity-Relationship Model  

#### 1. Identify the Participating Entities  
The main entities in the system are:  
- **Product**: Represents the items available for sale.  
- **Category**: Groups products into nested categories.  
- **Image**: Stores images for each product.  
- **User**: Represents both shoppers and administrators.  
- **Shopper**: A type of user who can place orders.  
- **Administrator**: A type of user with management privileges.  
- **Order**: Represents a purchase made by a shopper.  
- **OrderItem**: Individual products within an order.  
- **ShippingAddress**: Stores multiple addresses for shoppers.  

#### 2. Identify the Relations  
- **A Product belongs to multiple Categories (Many-to-Many).**  
- **A Product has multiple Images (One-to-Many).**  
- **A Shopper can place multiple Orders (One-to-Many).**  
- **An Order contains multiple OrderItems (One-to-Many).**  
- **A Shopper can have multiple ShippingAddresses (One-to-Many).**  
- **A Category can have a parent category (Self-referencing One-to-Many).**  
- **An OrderItem can have independent statuses (Shipped, Canceled, Returned).**  

#### 3. Identify the Key Attributes  
- **Primary Keys:**  
  - `ProductID` (Product)  
  - `CategoryID` (Category)  
  - `ImageID` (Image)  
  - `UserID` (User, Shopper, Administrator)  
  - `OrderID` (Order)  
  - `OrderItemID` (OrderItem)  
  - `AddressID` (ShippingAddress)  

- **Foreign Keys:**  
  - `ProductID` and `CategoryID` in `ProductCategory` (Bridge Table)  
  - `ShopperID` in `Order`  
  - `ProductID` in `Image`  
  - `OrderID` in `OrderItem`  
  - `ShopperID` in `ShippingAddress`  

#### 4. ER Diagram  
*(Attach the ER diagram image here or use a code block if using Markdown-supported diagram syntax.)*  

---

### Assignment 2: Normalization  

#### **Normalization Techniques Explained**  
Normalization is the process of organizing data to minimize redundancy and dependency. The main normal forms are:  

#### **1st Normal Form (1NF)**  
- Each column should have atomic values (no multiple values in a single field).  
- **Example:**  

| OrderID | ProductNames      | Quantity |  
|---------|------------------|----------|  
| 101     | "Laptop, Mouse"  | 1, 2     | ❌ *Not in 1NF*  
| 101     | "Laptop"         | 1        | ✔ *1NF Applied*  
| 101     | "Mouse"          | 2        |  

#### **2nd Normal Form (2NF)**  
- Remove **partial dependencies** (fields should depend on the whole primary key, not part of it).  
- **Example:**  

| OrderID | ProductID | ProductName | OrderDate  |  
|---------|----------|-------------|------------|  
| 101     | 1        | Laptop      | 2024-03-19 |  
| 101     | 2        | Mouse       | 2024-03-19 |  

Since `ProductName` depends only on `ProductID`, move it to a separate `Product` table.

#### **3rd Normal Form (3NF)**  
- Remove **transitive dependencies** (non-key columns should depend only on the primary key).  
- **Example:**  

| OrderID | CustomerID | CustomerName | Address |  
|---------|-----------|--------------|---------|  
| 101     | 501       | John Doe     | NY, USA |  

Since `CustomerName` and `Address` depend on `CustomerID` (not `OrderID`), move them to a separate `Customer` table.

---

### Assignment 3: Installing and Connecting to MySQL  

#### **Step 1: Download and Install MySQL**  
1. Visit [MySQL Downloads](https://dev.mysql.com/downloads/installer/).  
2. Download **MySQL Installer** for Windows.  
3. Run the installer and choose:  
   - **MySQL Server**  
   - **MySQL Workbench** (for GUI)  
4. Set up a **root password** when prompted.  

#### **Step 2: Start MySQL Server**  
- Open **Command Prompt** and run:  
  ```sh
  net start mysql
