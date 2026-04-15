module myD(q,qn,d,clk,S,R);
input d,clk,S,R;
output q,qn;
reg q,qn;
   always @(posedge clk or negedge R or negedge S)
     if(!R)
        begin q<=0;qn<=1; end
     else if(!S)
        begin q<=1;qn<=0; end
     else 
        begin q<=d;qn<=~d; end
        
endmodule 