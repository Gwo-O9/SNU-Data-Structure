// LongInt ADT for unbounded integers

public class LongInt { 

  int[] LargeNumber;
  boolean plusminus = true;
  // constructor
  public LongInt(String s) {
    LargeNumber = new int[s.length()+1];

    int s_length = s.length();
    int i = 0;
    if(s.charAt(0) == '-'){
      plusminus = false;
      i++;
    }

    for(; i<s_length; i++){
      LargeNumber[s_length-i-1] = s.charAt(i) - 48; // ASCII Code -> Integer value
    }
  }

  public LongInt(int[] var){
    this.LargeNumber = var;
  }
  // returns 'this' + 'opnd'; Both inputs remain intact.
  public LongInt add(LongInt opnd) {
    
    int LengthA = this.LargeNumber.length;
    int LengthB = opnd.LargeNumber.length;
    int max_length = LengthA > LengthB ? LengthA +1 : LengthB +1;
    int[] output = new int[max_length+1];

    int[] LargeA = new int[max_length+1];
    int[] LargeB = new int[max_length+1];

    for(int i = 0; i<LengthA-1;i++){
      LargeA[i] = this.LargeNumber[i];
    }
    for(int i = 0; i<LengthB-1;i++){
      LargeB[i] = opnd.LargeNumber[i];
    }

    int Biggerone = 'A';
    if((this.plusminus == false && opnd.plusminus == true)){
      for(int i = 0; i<max_length;i++){
        if(LargeA[i] == LargeB[i]) continue;
        else if(LargeA[i] > LargeB[i]){
          Biggerone = 'A';
        }
        else Biggerone = 'B';
      }
      if(Biggerone == 'A'){
        for(int i = 0; i < max_length-1; i++){
          int temp = LargeA[i] - LargeB[i] >= 0 ? LargeA[i] - LargeB[i] : LargeA[i] - LargeB[i] + 10;
          output[i] += temp;
          if(output[i] == -1){
            output[i] = 9;
            output[i+1] -= 1;
          }
          if(i+1 == max_length-1) break;
          if(LargeA[i] - LargeB[i] < 0){
            output[i+1] -= 1;
          }
        }
        output[max_length] = '-';
        return new LongInt(output);
      }
        else{
          for(int i = 0; i < max_length-1; i++){
            int temp = LargeB[i] - LargeA[i] >= 0 ? LargeB[i] - LargeA[i] : LargeB[i] - LargeA[i] + 10;
            output[i] += temp;
            if(output[i] == -1){
              output[i] = 9;
              output[i+1] -= 1;
            }
            if(i+1 == max_length-1) break;
            if(LargeB[i] - LargeA[i] < 0){
              output[i+1] -= 1;
            }
          }
          return new LongInt(output);
      }
    }
    if(this.plusminus == true && opnd.plusminus == false){
      for(int i = 0; i<max_length;i++){
        if(LargeA[i] == LargeB[i]) continue;
        else if(LargeA[i] > LargeB[i]){
          Biggerone = 'A';
        }
        else Biggerone = 'B';
      }
      if(Biggerone == 'A'){
        for(int i = 0; i < max_length-1; i++){
          int temp = LargeA[i] - LargeB[i] >= 0 ? LargeA[i] - LargeB[i] : LargeA[i] - LargeB[i] + 10;
          output[i] += temp;
          if(output[i] == -1){
            output[i] = 9;
            output[i+1] -= 1;
          }
          if(i+1 == max_length-1) break;
          if(LargeA[i] - LargeB[i] < 0){
            output[i+1] -= 1;
          }
        }
        return new LongInt(output);
      }
        else{
          for(int i = 0; i < max_length-1; i++){
            int temp = LargeB[i] - LargeA[i] >= 0 ? LargeB[i] - LargeA[i] : LargeB[i] - LargeA[i] + 10;
            output[i] += temp;
            if(output[i] == -1){
              output[i] = 9;
              output[i+1] -= 1;
            }
            if(i+1 == max_length-1) break;
            if(LargeB[i] - LargeA[i] < 0){
              output[i+1] -= 1;
            }
          }
          output[max_length] = '-';
          return new LongInt(output);
      }
    }

    for(int i = 0; i <= max_length-1 ; i++){
      int temp = 0;
      temp = LargeA[i] + LargeB[i];
      output[i] += temp % 10;
      if(output[i] >= 10){
        int temp2 = output[i];
        output[i] = output[i]%10;
        output[i+1] = temp2/10;
      }
      if(i+1 == max_length-1) break;
      output[i+1] += temp / 10;
    }

    if(this.plusminus == false && opnd.plusminus == false){
      output[max_length] = '-';
      return new LongInt(output);
    }
  
    return new LongInt(output);
  }

  // returns 'this' - 'opnd'; Both inputs remain intact.
  public LongInt subtract(LongInt opnd) {
    int LengthA = this.LargeNumber.length;
    int LengthB = opnd.LargeNumber.length;
    int max_length = LengthA > LengthB ? LengthA: LengthB;
    int[] output = new int[max_length+1];

    int[] LargeA = new int[max_length+1];
    int[] LargeB = new int[max_length+1];

    for(int i = 0; i<LengthA-1;i++){
      LargeA[i] = this.LargeNumber[i];
    }
    for(int i = 0; i<LengthB-1;i++){
      LargeB[i] = opnd.LargeNumber[i];
    }

    if(this.plusminus == false && opnd.plusminus == true){
      for(int i = 0; i <= max_length-1 ; i++){
        int temp = 0;
        temp = LargeA[i] + LargeB[i];
        output[i] += temp % 10;
        if(output[i] >= 10){
          int temp2 = output[i];
          output[i] = output[i]%10;
          output[i+1] = temp2/10;
        }
        if(i+1 == max_length-1) break;
        output[i+1] += temp / 10;
      }
      output[max_length] = '-';
      return new LongInt(output);
    }

    if(this.plusminus == true && opnd.plusminus == false){
      for(int i = 0; i <= max_length-1 ; i++){
        int temp = 0;
        temp = LargeA[i] + LargeB[i];
        output[i] += temp % 10;
        if(output[i] >= 10){
          int temp2 = output[i];
          output[i] = output[i]%10;
          output[i+1] = temp2/10;
        }
        if(i+1 == max_length-1) break;
        output[i+1] += temp / 10;
      }
      return new LongInt(output);
    }

    char Biggerone = 'A';
    if(this.plusminus == false && opnd.plusminus == false){
        for(int i = 0; i<max_length;i++){
          if(LargeA[i] == LargeB[i]) continue;
          else if(LargeA[i] > LargeB[i]){
            Biggerone = 'A';
          }
          else Biggerone = 'B';
        }
        if(Biggerone == 'A'){
          for(int i = 0; i < max_length-1; i++){
            int temp = LargeA[i] - LargeB[i] >= 0 ? LargeA[i] - LargeB[i] : LargeA[i] - LargeB[i] + 10;
            output[i] += temp;
            if(output[i] == -1){
              output[i] = 9;
              output[i+1] -= 1;
            }
            if(i+1 == max_length-1) break;
            if(LargeA[i] - LargeB[i] < 0){
              output[i+1] -= 1;
            }
          }
          output[max_length] = '-';
          return new LongInt(output);
        }
          else{
            for(int i = 0; i < max_length-1; i++){
              int temp = LargeB[i] - LargeA[i] >= 0 ? LargeB[i] - LargeA[i] : LargeB[i] - LargeA[i] + 10;
              output[i] += temp;
              if(output[i] == -1){
                output[i] = 9;
                output[i+1] -= 1;
              }
              if(i+1 == max_length-1) break;
              if(LargeB[i] - LargeA[i] < 0){
                output[i+1] -= 1;
              }
            }
            return new LongInt(output);
        }
    }
    if(this.plusminus == true && opnd.plusminus == true){
    for(int i = 0; i<max_length;i++){
      if(LargeA[i] == LargeB[i]) continue;
      else if(LargeA[i] > LargeB[i]){
        Biggerone = 'A';
      }
      else Biggerone = 'B';
    }
    if(Biggerone == 'A'){
      for(int i = 0; i < max_length-1; i++){
        int temp = LargeA[i] - LargeB[i] >= 0 ? LargeA[i] - LargeB[i] : LargeA[i] - LargeB[i] + 10;
        output[i] += temp;
        if(output[i] == -1){
          output[i] = 9;
          output[i+1] -= 1;
        }
        if(i+1 == max_length-1) break;
        if(LargeA[i] - LargeB[i] < 0){
          output[i+1] -= 1;
        }
      }
      return new LongInt(output);
    }
      else{
        for(int i = 0; i < max_length-1; i++){
          int temp = LargeB[i] - LargeA[i] >= 0 ? LargeB[i] - LargeA[i] : LargeB[i] - LargeA[i] + 10;
          output[i] += temp;
          if(output[i] == -1){
            output[i] = 9;
            output[i+1] -= 1;
          }
          if(i+1 == max_length-1) break;
          if(LargeB[i] - LargeA[i] < 0){
            output[i+1] -= 1;
          }
        }
        output[max_length] = '-';
        return new LongInt(output);
    }
}
for(int i = 0; i < max_length-1; i++){
  int temp = LargeA[i] - LargeB[i] >= 0 ? LargeA[i] - LargeB[i] : LargeA[i] - LargeB[i] + 10;
  output[i] += temp;
  if(output[i] == -1){
    output[i] = 9;
    output[i+1] -= 1;
  }
  if(i+1 == max_length-1) break;
  if(LargeA[i] - LargeB[i] < 0){
    output[i+1] -= 1;
  }
}
return new LongInt(output);
  }

  // returns 'this' * 'opnd'; Both inputs remain intact.
  public LongInt multiply(LongInt opnd) {
    int LengthA = this.LargeNumber.length;
    int LengthB = opnd.LargeNumber.length;
    int max_length = LengthA + LengthB + 1;
    int[] output = new int[max_length];

    for(int i = 0; i<LengthA; i++){
      for(int j = 0; j<LengthB; j++){
        int temp = this.LargeNumber[i] * opnd.LargeNumber[j];
        output[i+j] += temp;
      }
    }

    for(int k = 0; k<max_length-1;k++){
      int temp = output[k]/10;
      output[k] = output[k]%10;
      if(k+1 == max_length -1) break;
      output[k+1] += temp;
    }

    if((this.plusminus == true && opnd.plusminus == false) || (this.plusminus == false && opnd.plusminus == true)){
      output[max_length-1] = '-';
    }
    return new LongInt(output);
  }

  // print the value of 'this' element to the standard output.
  public void print() {
    int pass = 0;
    if(LargeNumber[LargeNumber.length-1] == '-' || plusminus == false) {
      System.out.print('-'); 
    }
    for(int i = 0; i<LargeNumber.length-1; i++){
      if(LargeNumber[LargeNumber.length-1-i] == 0 || LargeNumber[LargeNumber.length-1-i] == '-') pass++;
      else break;
    }
    for(int i = LargeNumber.length-1-pass; i>=0; i--){
      
      System.out.print(LargeNumber[i]);
    }
  }
}