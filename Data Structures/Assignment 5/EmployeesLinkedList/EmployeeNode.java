    public class EmployeeNode implements Comparable<Object>{
        private String name;
        private double salary;
        private int age;
        private EmployeeNode next;

        public EmployeeNode(String name, double salary, int age){
            this.name = name;
            this.salary = salary;
            this.age = age;
            next = null;
        }

        @Override
        public int compareTo(Object o){
            EmployeeNode employee = (EmployeeNode)o;
            if(employee.getSalary() != this.getSalary()){
                return (this.getSalary() - employee.getSalary()) < 0 ? -1 : 1;
            }else if(employee.getAge() != this.getAge()){
                return (this.getAge() - employee.getAge()) < 0 ? -1 : 1;
            }
            return 0;
        }

        public double getSalary(){
            return salary;
        }

        public void setSalary(double salary){
            this.salary = salary;
        }

        public int getAge(){
            return age;
        }

        public void setAge(int age){
            this.age = age;
        }

        public EmployeeNode getNext() {
            return next;
        }

        public void setNext(EmployeeNode next) {
            this.next = next;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }