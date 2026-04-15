module My74161(clk,ldn,rdn,ep,et,d3,d2,d1,d0,q3,q2,q1,q0,co);
input clk,ldn,rdn,ep,et,d3,d2,d1,d0;
output q3,q2,q1,q0,co;

reg[3:0] Q;
reg q3,q2,q1,q0,co;

always @(posedge clk or negedge rdn)
  begin
      if(!rdn)
        Q <=4'b0000;
      else if(!ldn)
      begin Q[3] <= d3;Q[2] <=d2;Q[1] <=d1;Q[0] <=d0; end
      else if((ep == 1'b1)&&(et == 1'b1))
        Q <=Q + 4'b0001;
  end
always @(Q,et)
  begin
      co <=Q[3] &&Q[2]&&Q[1]&&Q[0]&&et;
      q3 <=Q[3] ;q2 <=Q[2];q1<=Q[1];q0 <=Q[0];
  end
endmodule
