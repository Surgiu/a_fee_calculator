# a_fee_calculator
This Java program was built for my friend who found it upsetting to calculate how much he would spend on accommodation during the second vacation of college. For the sake of lightness, I give up using the MVC structure. 

The Chinese version of the charging rule is shown in the following lines:

    一个成人的价格是1799，一个儿童不要床位的价格是1500，如果儿童需要床位则价格变为2099,
    如果需要单间房则需要补淡季1400，旺季1600的单间房差价
    除此之外65岁以上老人在成人基础上要加300元老人费用
    上面是基础套餐，两个人的套餐，
    下面将说明超过两人的收费标准，以及在旺季情况下增加的费用
    
    旺季情况下每个人都需要增加500元的旺季涨价费用
    
    现在有个超比例费用概念：
    因为优惠针对的是24到65岁的人，所以如果一个符合年龄段的人加上一个不符合年龄段的人我们称为不超比例，
    也就是1:1（不超:超），我们不收取超比例费用，
    如果超过这个比例，比如1:2（不超：超）我们便按超过一个人比例的费用多收取300元，
    同理1:3便多收取600元，而2:1（不超：超）不收取超比例费用，你可以理解为一个符合年龄段的人自动可以同化一
    个不符合年龄段的人，不符合年龄段的人额外要多收300元，但是由于之前有每个年龄段还有额外加的钱，
    所以你不能写在一个代码里面，你要单独写出来，才能不跟前面的代码冲突。

If your computer doesn't have JRE, you can click [here](https://github.com/Surgiu/a_fee_calculator/blob/master/resourse/zzx.jar) to download the `.exe` file.
