class Solution {
    public boolean isPalindrome(int x) {
         int rev=0;
         int ori=x;
       
         while(x>0){
            int sum = x%10;
            rev = rev*10+sum;
            x/=10;
         }
         if(rev==ori){
            return true;
         }else{
            return false;
         }
        
    }
}