

/**
 *1、身份证号码[1-->17位]分别对应的系数为[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2]
 *2、将[前17位数字*系数]之和，再除以11得到余数[0-->10]对应身份证号码最后一位的顺序为[1,0,'X',9,8,7,6,5,4,3,2]
 */
function validCard(cardId,csrq,xb){
	var arr = new Array(3);
	cardId = cardId.toUpperCase();//字母转大写
    if(cardId.length==18){//∑(ai×Wi)(mod 11),18位身份证需要验证最后一位校验位
    	var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        if(!city[cardId.substr(0,2)]){
        	arr[0]="身份证地址编码错误";
        	return arr;
        }
        var cardIdArr=cardId.split('');
        var factor=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];//加权因子
        var parity=[1,0,'X',9,8,7,6,5,4,3,2];//校验位
        var sum=0,ai=0,wi=0;
        for (var i=0;i<17;i++){
            ai=cardIdArr[i];
            wi=factor[i];
            sum+=ai*wi;
        }
        var last=parity[sum%11];
        if(parity[sum%11]!=cardIdArr[17]){
        	arr[0]="身份证号校验错误";
        	return arr;
        }else{
        	if(xb!=null&&((cardId.substr(16,1)%2==0&&xb=="01")||(cardId.substr(16,1)%2!=0&&xb=="00"))){
            	arr[0]="身份证性别校验错误";
            	return arr;
            }
        	if(csrq!=null&&cardId.substr(6,8)!=csrq.replace(/-/g, "")){
            	arr[0]="身份证出生日期校验错误";
            	return arr;
            }
        }
    }else{
    	arr[0]="身份证位数校验错误";
    	return arr;
    }
    arr[0]="true";
    arr[1]=cardId.substr(6,4)+"-"+cardId.substr(10,2)+"-"+cardId.substr(12,2);//生日
    arr[2]=cardId.substr(16,1)%2!=0?"01":"00";//性别
    return arr;
}
