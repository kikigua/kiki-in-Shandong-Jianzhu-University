// 轮播图地址、标题和链接
var slideImages = [
    {imagePath: 'images/1.jpg', caption: '航拍南京五一出城车辆：“小铁盒”缓慢前行', go: '1.html'},
    {imagePath: 'images/2.jpg', caption: '西湖断桥客流爆满 美女城管巡逻队帅气吸睛', go: '2.html'},
    {imagePath: 'images/3.jpg', caption: '载45人客车爆燃 瞬间被烧成空架', go: '3.html'},
    {imagePath: 'images/4.jpg', caption: '寒流袭英法 酿酒商点蜡烛为葡萄取暖', go: '4.html'},
    {imagePath: 'images/5.jpg', caption: '即将首飞！中国AG600两栖飞机完成首次滑行', go: '5.html'},
];

var currentImageId = 0;
var timer;
var slideWrap;
window.onload = function() {
    slideWrap = document.getElementById('slideWrap');
    var ol = slideWrap.children[0];
    showImage();
    play();    
    slideWrap.onmouseenter = function() {
        clearInterval(timer);
    };
    slideWrap.onmouseleave = function() {
        play();
    }
    ol.onmouseover = function(e) {
        var target = e.target;
        if(target.tagName == 'LI' && e.relatedTarget.tagName != 'A') {
            console.log(target.getAttribute('liId'));
            currentImageId = parseInt(target.getAttribute('liId'));
            showImage();
        }
    }

    var spnLoginText = document.getElementById('spnLoginText');
    var dvLoginBox = document.getElementById('dvLoginBox');
    var spnCloseButton = document.getElementById('spnCloseButton');
    spnLoginText.onmouseover = dvLoginBox.onmouseover = showLoginBox;
    spnLoginText.onmouseout = dvLoginBox.onmouseout = spnCloseButton.onclick = closeLoginBox;
}

function showImage() {
    var slideImg = document.getElementById('linkImg').children[0];
    slideImg.src = slideImages[currentImageId].imagePath;
    var caption = document.getElementsByClassName('caption')[0];
    caption.textContent = slideImages[currentImageId].caption;
    caption.href = slideImages[currentImageId].go;
    var lis = slideWrap.getElementsByTagName('li');
    if(document.getElementsByClassName('active').length > 0)
        document.getElementsByClassName('active')[0].classList.remove('active');
    lis[currentImageId].classList.add('active');
}

function play() {
    timer = setInterval(function() {    
        currentImageId = currentImageId === 4 ? 0 : currentImageId + 1;
        showImage();
    }, 2000);
}

function showLoginBox() {
    dvLoginBox.style.display = 'block';
    dvLoginBox.style.left = spnLoginText.offsetLeft + spnLoginText.clientWidth/2 - dvLoginBox.clientWidth/2 + 'px';
    document.getElementById('spnLoginText').classList.add('spnLoginText_hover');
}

function closeLoginBox() {
    dvLoginBox.style.display = 'none';
    document.getElementById('spnLoginText').classList.remove('spnLoginText_hover');
}