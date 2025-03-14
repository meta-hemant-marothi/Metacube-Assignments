# Assignment

Implement the immutable class intSet using an array to represent a set of integers in the range 1-1000. It should support public methods like - 

1. isMember(int x) - check whether x is a member of the set and return a boolean value

2. size() - return the size of the set

3. isSubSet(intSet s) - check whether s is a subset of the set

4. getComplement()  - return the complement set, you can assume that 1..1000 is the universal set

5. union( intSet s) - return the union set

6. You may use private helper methods. 

### Set operations -

1. Union: Set of members that belong to the first set "or" the second set. 
2. Intersection: Set of members that belong to both the first set "and" second set.
4. Difference: Set of members that belong to the first set "and not" the second set. 
3. Complement: Set of members that belong to the second (universal) set "and not" the first set. 

###### Given the following sets:

   A={2,4,6,8,10}
   B={1,2,3,4,5}

   Union:        A∪B = {1,2,3,4,5,6,8,10}
   Intersection: A∩B = {2,4}
   Difference:   A-B = {6,8,10}
   Complement:   AB = {1,3,5}
