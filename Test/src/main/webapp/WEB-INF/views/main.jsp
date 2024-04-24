<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>My Shop</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="shortcut icon"
      type="image/x-icon"
      href="https://play-lh.googleusercontent.com/TBRwjS_qfJCSj1m7zZB93FnpJM5fSpMA_wUlFDLxWAb45T9RmwBvQd5cWR5viJJOhkI"
    />
    <link rel="stylesheet" href="./css/pages/main.css" />
    <script
      src="https://kit.fontawesome.com/48e38ddfaa.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <header class="header">
      <div class="header__top">
        <nav class="header__nav-box">
          <ul class="header__list-box">
            <li class="header__content">BRAND</li>
            <li class="header__content">EVENT</li>
            <li class="header__content">LOOKBOOK</li>
            <li class="header__content">COMMUNITY</li>
          </ul>
        </nav>
        <h1 class="header__title">EZENSA</h1>
        <ul class="header__menu">
          <li class="header__content">
            <i class="fa-solid fa-magnifying-glass fa-lg"></i>
          </li>
          <li class="header__content header__content--badge-box">
            <div class="header__user-badge">
              <span></span>
            </div>
            
            
            <script>
			  // JavaScript를 사용하여 사용자 ID를 가져와서 HTML에 추가
			  var userid = '<%= request.getAttribute("userid") %>';
			  document.getElementById('useridSpan').innerText = userid;
			</script>
			
			<li class="header__content header__content--badge-box">
			  <div class="header__user-badge">
			    <span></span>
			  </div>
			  
			      <script>
        // JavaScript를 사용하여 사용자 ID를 가져와서 HTML에 추가
        var userId = '<%= request.getAttribute("userData") %>'; // MainAction.java에서 설정한 attribute 이름 사용
        document.getElementById('userIdSpan').innerText = userId;
        
        // 사용자 아이콘을 설정 (예시로 사용자 ID가 'admin'일 때만 아이콘 변경)
        var userIcon = document.getElementById('userIcon');
        if (userId === 'admin') {
            userIcon.className = 'fa-solid fa-user fa-lg'; // 변경할 아이콘 클래스 설정
        } else {
            userIcon.className = 'fa-regular fa-user fa-lg'; // 기본 아이콘 클래스 설정
        }
    </script>
			  
			  <i class="fa-regular fa-user fa-lg" ><span id="userIdSpan"></span></i>
			</li>

          <li class="header__content header__content--badge-box">
            <div class="header__cart-badge"><span>2</span></div>
            <i class="fa-solid fa-cart-shopping fa-lg"></i>
          </li>
          <li class="header__content">
            <i class="fa-solid fa-bars fa-lg"></i>
          </li>
        </ul>
      </div>
      <div class="header__bottom">
        <ul class="header__category">
          <li class="header__content">BEST</li>
          <li class="header__content">NEW5%</li>
          <li class="header__content">1+1</li>
          <li class="header__content"><a href="./outer.html">OUTER</a></li>
          <li class="header__content">TOP</li>
          <li class="header__content">SHIRTS</li>
          <li class="header__content">DRESS</li>
          <li class="header__content">PANTS</li>
          <li class="header__content">ACC</li>
        </ul>
      </div>
    </header>
    <main class="main">
      <section class="main__slider">
        <h2 class="main__slider-title">MY SHOP</h2>
        <span class="main__slider-btn"
          ><i class="fa-solid fa-chevron-left fa-xl"></i
        ></span>
        <div class="main__slider-container">
          <span class="main__slider-text">STYLE IN EVERY DAY</span>
          <div class="main__slider-image-container">
            <img
              class="main__slider-image"
              src="assets/main-slider.jpg"
              alt="main slider image"
            />
          </div>
          <span class="main__slider-text">FOR SPRING SET-UP</span>
        </div>
        <span class="main__slider-btn"
          ><i class="fa-solid fa-chevron-right fa-xl"></i
        ></span>
        <div class="main__progress-bar">
          <div
            class="main__progress-bar-item main__progress-bar-item--visible"
          ></div>
          <div
            class="main__progress-bar-item main__progress-bar-item--invisible"
          ></div>
          <div
            class="main__progress-bar-item main__progress-bar-item--invisible"
          ></div>
        </div>
      </section>
      <section class="main__introduction">
        <div class="main__intro-container-img">
          <img class="main__intro-image" src="./assets/intro-01.jpg" alt="" />
          <div class="main__intro-text-container">
            <h2 class="main__intro-title">OPEN EVENT</h2>
            <span class="main__intro-description"
              >이번주 특가 이벤트 만나보세요</span
            >
            
            

            <form action="CommandServlet" method="post">
    <input type="hidden" name="command" value="mypage_form">
    <input type="submit" value="마이페이지">
</form>
<form action="CommandServlet" method="post">
    <input type="hidden" name="command" value="refund_form">
    <input type="submit" value="교환&환불">
</form>
<form action="ShoppingServlet" method="post">
    <input type="hidden" name="command" value="product_list">
    <input type="submit" value="상품페이지">
</form>
            
            
            
            
            
            
            
            
          </div>
        </div>
        <div class="main__intro-container">
          <h2 class="main__intro-title main__intro-title--black">MADE BY EZEN</h2>
          <span class="main__intro-description main__intro-description--black"
            >1+1 이벤트</span
          >
        </div>
        <div class="main__intro-container-img">
          <img class="main__intro-image" src="./assets/intro-02.jpg" alt="" />
          <div class="main__intro-text-container">
            <h2 class="main__intro-title">SWEATER SERIES</h2>
            <span class="main__intro-description">다시 없을 특가</span>
          </div>
        </div>
        <div class="main__intro-container-img">
          <img class="main__intro-image" src="./assets/intro-03.jpg" alt="" />
          <div class="main__intro-text-container">
            <h2 class="main__intro-title">SHOWROOM</h2>
            <span class="main__intro-description">HTML, CSS</span>
          </div>
        </div>
      </section>
      <section class="main__product-list">
        <h3 class="main__product-list-title">Best of the month</h3>
        <span class="main__product-list-sub-title"
          >이달의 베스트 상품이에요</span
        >
        <div class="main__product-list-sub-container">
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-01.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-02.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-03.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-04.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
        </div>
        <div class="main__product-list-sub-container">
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-01.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-02.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-03.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
          <article class="main__product-list-article">
            <div class="main__article-img-container">
              <img class="main__article-img" src="./assets/best-04.jpg" />
            </div>
            <div class="main__article-container">
              <div
                class="main__article-color-item main__article-color-item--01"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--02"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--03"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--04"
              ></div>
              <div
                class="main__article-color-item main__article-color-item--05"
              ></div>
            </div>
            <div class="main__article-container">
              <h4 class="main__article-product-name">
                모먼트 베이직 슬림 무지 티셔츠
              </h4>
            </div>
            <div class="main__article-container">
              <span class="main__article-product-description">
                #편하게 입을 수 있어요 #간편해요 #데일리룩으로 좋아요
              </span>
            </div>
            <div class="main__article-container">
              <span class="main__article-total-price">15,000원</span>
              <small class="main__article-previous-price">23,000원</small>
              <span class="main__article-discount-rate">40%</span>
            </div>
          </article>
        </div>
      </section>
    </main>
    <footer class="footer">
      <div class="footer__sub-box">
        <span class="footer__title">EZENSA</span>
        <small class="footer__text">사업자등록번호: 000-0000-0000</small>
        <small class="footer__text">주소: 수원시 팔달구</small>
        <small class="footer__text footer__copy-right"
          >&copy; All right reserved by EZEN</small
        >
      </div>
      <div class="footer__sub-box footer__social-box">
        <span class="footer__text"
          ><i class="fa-brands fa-twitter fa-2xl"></i
        ></span>
        <span class="footer__text"
          ><i class="fa-brands fa-facebook fa-2xl"></i
        ></span>
        <span class="footer__text"
          ><i class="fa-brands fa-instagram fa-2xl"></i
        ></span>
      </div>
    </footer>
  </body>
</html>
