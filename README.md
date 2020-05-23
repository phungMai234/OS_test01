# OS_Người sản xuất - Người dùng
#Bài 4 trang 28


#Nhà sản xuất:
do {
// produce an item in nextp
 wait (empty);
 wait (mutex);
 // add the item to the buffer
 signal (mutex);
 signal (full);
 } while (TRUE); 
 
 
 
 #Người dùng
 do {
 wait (full);
 wait (mutex);
 // remove an item from buffer to nextc
 signal (mutex);
 signal (empty);
 // consume the item in nextc
 } while (TRUE); 
