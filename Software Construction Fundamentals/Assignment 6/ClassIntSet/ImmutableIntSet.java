public class ImmutableIntSet {
    private final boolean[] setOfElements;
    public static final int UNIVERSAL_SET_SIZE = 1000;

    public ImmutableIntSet(int[] nums) {
        setOfElements = new boolean[ImmutableIntSet.UNIVERSAL_SET_SIZE + 1];
        for(int num : nums){
            setOfElements[num] = true;
        }
    }

    public boolean isMember(int num){
        return (num >= 0 && num <= 1000 && setOfElements[num]);
    }

    public int size(){
        int count = 0;
        for(boolean bool : setOfElements){
            if(bool)count++;
        }
        return count;
    }
    
    public boolean isSubSet(ImmutableIntSet s){
        for(int i = 1; i <= UNIVERSAL_SET_SIZE; i++){
            if(this.setOfElements[i] != s.isMember(i)){
                return false;
            }
        }
        return true;
    }

    public ImmutableIntSet getCompliment(){
        int[] complimentedSet = new int[UNIVERSAL_SET_SIZE - this.size()];
        int index = 0;
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if(!setOfElements[i]){
                complimentedSet[index++] = i;
            }
        }
        return new ImmutableIntSet(complimentedSet);
    }

    public ImmutableIntSet union(ImmutableIntSet s){
        int[] unionSet = new int[s.size() + this.size()];
        int index = 0;
        for(int i = 1; i <= UNIVERSAL_SET_SIZE; i++){
            if(this.setOfElements[i] || s.isMember(i)){
                unionSet[index++] = i;
            }
        }
        return new ImmutableIntSet(unionSet);
    }

    public ImmutableIntSet difference(ImmutableIntSet s){
        int[] differenceSet = new int[this.size()];
        int index = 0;
        for(int i = 1; i <= UNIVERSAL_SET_SIZE; i++){
            if(this.setOfElements[i] && !s.isMember(i)){
                differenceSet[index++] = i;
            }
        }
        return new ImmutableIntSet(differenceSet);
    }

    public ImmutableIntSet intersection(ImmutableIntSet s){
        int[] intersectionSet = new int[s.size() + this.size()];
        int index = 0;
        for(int i = 1; i <= UNIVERSAL_SET_SIZE; i++){
            if(this.setOfElements[i] || s.isMember(i)){
                intersectionSet[index++] = i;
            }
        }
        return new ImmutableIntSet(intersectionSet);
    } 

    public void displaySet(){
        System.out.print("{ ");
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if(this.setOfElements[i]){
                System.out.print(i + ", ");
            }
        }
        System.out.println("}");
    }

    

}
