module myTwoSeg7(rst_n,clkin,Q7,Q6,Q5,Q4,Q3,Q2,Q1,Q0,dig_sel_n,seg_out);
input clkin,rst_n;
input Q7,Q6,Q5,Q4,Q3,Q2,Q1,Q0;
output [7:0] dig_sel_n,seg_out;

reg  [7:0] dig_sel_n,seg_out;
reg clk1ms; 
reg [2:0] cnt8;
integer cnt20k;
wire [7:0] value;

assign value={Q7,Q6,Q5,Q4,Q3,Q2,Q1,Q0};

function [7:0] seg7;
	input [3:0] data;
	begin
		case(data)
			0:  seg7= 8'b11000000; 
            1:  seg7= 8'b11111001;
            2:  seg7= 8'b10100100;
            3:  seg7= 8'b10110000; 
            4:  seg7= 8'b10011001; 
            5:  seg7= 8'b10010010;
            6:  seg7= 8'b10000010;
            7:  seg7= 8'b11111000;
            8:  seg7= 8'b10000000;
            9:  seg7= 8'b10010000;  
            default: seg7= 8'b11111111; 
         endcase
    end
endfunction
	
//20MHZ入1kHZ输出
always @(posedge clkin or negedge rst_n)
	begin
      if(!rst_n)
		cnt20k <=0; 
      else 
         begin
			   if(cnt20k == 19_999)
				 cnt20k <=0;
			   else 
				 cnt20k <= cnt20k + 1;
			   
			   if (cnt20k <10_000)
				  clk1ms <= 1'b1;
			   else
				  clk1ms <= 1'b0; 
		 end
	end

//counter 8
always @(posedge clk1ms)
	begin
      if(!rst_n)
         cnt8 <= 0;
      else 
         begin
			   if(cnt8 >= 7)
				 cnt8 <=0;
			   else 
				 cnt8 <= cnt8 + 1;
			  
		 end
	end


 //数码管    
always @(cnt8,value,rst_n)
    begin
        if(!rst_n)
            begin    dig_sel_n <= 8'b11111111; seg_out <=8'b11111111; end        
        else
            case(cnt8)
				0:  begin dig_sel_n <= 8'b11111110; seg_out <= seg7(value%10); end
				1:  begin dig_sel_n <= 8'b11111101; seg_out <= seg7(value/10%10); end
				2:  begin dig_sel_n <= 8'b11111011; seg_out <= seg7(value/100%10);end
				3:  begin dig_sel_n <= 8'b11110111; seg_out <= seg7(value/1000%10); end
			    4:  begin dig_sel_n <= 8'b11101111; seg_out <= seg7(value/10000%10); end
			    5:  begin dig_sel_n <= 8'b11011111; seg_out <= seg7(value/100000%10);end
			    6:  begin dig_sel_n <= 8'b10111111; seg_out <= seg7(value/1000000%10);end
			    7:  begin dig_sel_n <= 8'b01111111; seg_out <= seg7(value/10000000%10); end
				default:
					begin dig_sel_n <= 8'b11111111; seg_out <= 8'b11111111; end
            endcase

    end
    
endmodule

