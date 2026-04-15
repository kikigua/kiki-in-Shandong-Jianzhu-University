// 轮播图地址、标题和链接
var slideImages = [
    {imagePath: 'images/1.jpg', caption: '航拍南京五一出城车辆：“小铁盒”缓慢前行', go: '1.html'},
    {imagePath: 'images/2.jpg', caption: '西湖断桥客流爆满 美女城管巡逻队帅气吸睛', go: '2.html'},
    {imagePath: 'images/3.jpg', caption: '载45人客车爆燃 瞬间被烧成空架', go: '3.html'},
    {imagePath: 'images/4.jpg', caption: '寒流袭英法 酿酒商点蜡烛为葡萄取暖', go: '4.html'},
    {imagePath: 'images/5.jpg', caption: '即将首飞！中国AG600两栖飞机完成首次滑行', go: '5.html'},
];
window.onload = function(){
// 弹出层
var dvLoginBox = document.getElementById("dvLoginBox");
var spnLoginText= document.getElementById("spnLoginText");
    spnLoginText.onmouseenter=function(){
    dvLoginBox.style.display="block";
    spnLoginText.className="spnLoginText_hover";
    dvLoginBox.style.left="690px";
    }
    dvLoginBox.onmouseleave=function(){
    dvLoginBox.style.display="none";
    spnLoginText.className="";
    }
var spnCloseButton=document.getElementById("spnCloseButton");
    spnCloseButton.onclick=function(){
    dvLoginBox.style.display="none";
    spnLoginText.className="";
    }
    
//轮播图
//实现自动播放
var index=1;
function lunbo(){
    //获取img对象
    var img=document.getElementById("lunbo_img");
    var xiafang=document.getElementById("xiafang");
    img.src=slideImages[index].imagePath;
    xiafang.innerText=""+slideImages[index].caption;
    index++;
    //判断是否大于5
    if(index>5){
        index=1;
    }
    // //获取a标签中的href
    // var href_a=document.getElementById("href");
    // href_a.href=""
}
    //设置定时器
    timer=setInterval(lunbo,2000);
    /*切记定时器里调用lunbo方法不能加(),setInterval(lunbo,2000);如果加()会执行lunbo（）方法，而导致定时器没用。*/
    //鼠标移入时，移除计时器
    var img=document.getElementById("lunbo_img");
    img.onmouseenter=function(){
        clearInterval(timer);
    }
    img.onmouseleave=function(){
        timer=setInterval(lunbo,2000);
    }

    //！！！！
    //图片锁定
    var liid_id=document.getElementsByTagName("li");
    for(let i=0;i<liid_id.length;i++){//这里用let，不用var，原因：作用域问题，这js先解析，再运行
        liid_id[i].onmouseenter=function(){
            clearInterval(timer);
            //获取img对象
            var img=document.getElementById("lunbo_img");
            var xiafang=document.getElementById("xiafang");
            img.src=slideImages[i].imagePath;
            xiafang.innerText=""+slideImages[i].caption;
        }
        liid_id[i].onmouseleave=function(){
                //设置定时器
                timer=setInterval(lunbo,2000);
        }
    }
}
