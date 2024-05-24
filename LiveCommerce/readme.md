drop table reviews;
drop table kakaopayments;
drop table livestreams;
drop table payments;
drop table orders;
drop table tbl_users_auth;
drop table cart;
drop table livechat;
drop table events;
drop table users;
drop table products;
drop table categories;

-- 사용자 정보 테이블
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT, -- 사용자 ID
    Username VARCHAR(20) NOT NULL, -- 사용자 이름
    Email VARCHAR(50) NOT NULL, -- 사용자 이메일
    Password VARCHAR(100) NOT NULL, -- 사용자 비밀번호
    ShippingAddress VARCHAR(1000) NOT NULL, -- 배송 주소
    ShippingPostalCode VARCHAR(20) NOT NULL, -- 배송 우편번호
    enabled char(1) default '0'
);

-- 카테고리 정보 테이블
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT, -- 카테고리 ID
    CategoryName VARCHAR(50) NOT NULL -- 카테고리명
);

-- 제품 정보 테이블
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT, -- 제품 ID
    ProductName VARCHAR(50) NOT NULL, -- 제품명
    Description TEXT, -- 제품 설명
    Price INT NOT NULL, -- 제품 가격
    Photo VARCHAR(255), -- 제품 사진 경로
    CategoryID INT, -- 외래 키: 카테고리 ID
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- 주문 정보 테이블
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT, -- 주문 ID
    UserID INT, -- 주문한 사용자의 ID (외래키)
    ProductID INT, -- 주문한 제품의 ID (외래키)
    Quantity INT, -- 주문 수량
    ShippingAddress VARCHAR(1000) NOT NULL, -- 배송 주소
    ShippingPostalCode VARCHAR(20) NOT NULL, -- 배송 우편번호
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 주문 일자
    FOREIGN KEY (UserID) REFERENCES Users(UserID), -- 사용자 테이블과의 외래키 관계
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) -- 제품 테이블과의 외래키 관계
);

-- 결제 정보 테이블
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT, -- 결제 ID
    OrderID INT, -- 주문 ID (외래키)
    PaymentMethod VARCHAR(50) NOT NULL, -- 결제 수단
    Amount INT NOT NULL, -- 결제 금액
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 결제 일자
    Status VARCHAR(50), -- 결제 상태
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) -- 주문 테이블과의 외래키 관계
);

CREATE TABLE LiveChat (
    ChatID INT PRIMARY KEY AUTO_INCREMENT, -- 채팅 ID
    UserID INT, -- 사용자 ID (외래키)
    Message TEXT, -- 채팅 메시지
    ChatTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 채팅 시간
    ProductID INT, -- 제품 ID (외래키)
    FOREIGN KEY (UserID) REFERENCES Users(UserID), -- 사용자 테이블과의 외래키 관계
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) -- 제품 테이블과의 외래키 관계
);

-- 장바구니 테이블
CREATE TABLE Cart (
    CartID INT PRIMARY KEY AUTO_INCREMENT, -- 장바구니 ID
    UserID INT, -- 사용자 ID (외래키)
    ProductID INT, -- 제품 ID (외래키)
    Quantity INT, -- 제품 수량
    FOREIGN KEY (UserID) REFERENCES Users(UserID), -- 사용자 테이블과의 외래키 관계
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) -- 제품 테이블과의 외래키 관계
);

-- 카카오톡 결제 정보 테이블
CREATE TABLE KakaoPayments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT, -- 결제 ID
    OrderID INT, -- 주문 ID (외래키)
    PaymentMethod VARCHAR(50) NOT NULL, -- 결제 수단
    Amount INT NOT NULL, -- 결제 금액
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 결제 일자
    KakaoTransactionID VARCHAR(100) NOT NULL, -- 카카오 거래 ID
    Status VARCHAR(50), -- 결제 상태
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) -- 주문 테이블과의 외래키 관계
);

-- 이벤트 정보 테이블
CREATE TABLE Events (
    EventID INT PRIMARY KEY AUTO_INCREMENT, -- 이벤트 ID
    EventName VARCHAR(100) NOT NULL, -- 이벤트명
    Description TEXT, -- 이벤트 설명
    EventDate TIMESTAMP NOT NULL, -- 이벤트 일자
    Location VARCHAR(200) NOT NULL, -- 이벤트 장소
    Photo VARCHAR(255), -- 이벤트 사진 경로
    ProductID INT, -- 상품 ID (외래키)
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) -- 제품 테이블과의 외래키 관계
);

-- 후기 정보 테이블
CREATE TABLE Reviews (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT, -- 후기 ID
    UserID INT, -- 사용자 ID (외래키)
    ProductID INT, -- 제품 ID (외래키)
    Rating INT NOT NULL, -- 평점
    Comment TEXT, -- 후기 내용
    ReviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 후기 작성 일자
    FOREIGN KEY (UserID) REFERENCES Users(UserID), -- 사용자 테이블과의 외래키 관계
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) -- 제품 테이블과의 외래키 관계
);

CREATE TABLE tbl_users_auth (
    userid INT NOT NULL,
    auth VARCHAR(100) NOT NULL,
    CONSTRAINT fk_users_auth FOREIGN KEY (userid) REFERENCES Users(UserID)
);
CREATE TABLE LiveStreams (
    StreamID INT PRIMARY KEY AUTO_INCREMENT, -- 스트리밍 ID
    Title VARCHAR(100) NOT NULL, -- 제목
    VideoID VARCHAR(50) NOT NULL, -- 비디오 ID
    Description TEXT, -- 설명
    StartTime TIMESTAMP NOT NULL, -- 시작 시간
    EndTime TIMESTAMP, -- 종료 시간
    Photo VARCHAR(255), -- 사진의 경로를 저장할 열
    ProductID INT, -- 외래 키: 상품 ID
    ChatID INT, -- 외래 키: 채팅 ID
    FOREIGN KEY (ProductID) REFERENCES products(ProductID),
    FOREIGN KEY (ChatID) REFERENCES livechat(ChatID)
);

INSERT INTO Categories (CategoryName)
VALUES ('상의'),
       ('하의'),
       ('신발'),
       ('액세서리'),
       ('가방'),
       ('아우터'),
       ('전자기기'),
       ('가전제품'),
       ('서적'),
       ('가구');

        INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID)
VALUES
    ('티셔츠', '매일 입기 좋은 편안한 면 소재의 티셔츠', 20000, 'https://wooyoungmi.com/web/product/big/202401/74c3a9a5e62b03af0d498999479157bd.jpg', 1),
    ('청바지', '캐주얼한 모임에 적합한 클래식한 청바지', 30000, 'https://img1.shopcider.com/product/1679850218000-tyhe2w.jpg?x-oss-process=image/resize,w_1050,m_lfit/quality,Q_60/interlace,1', 2),
    ('운동화', '활동적인 라이프스타일에 어울리는 스타일리시한 운동화', 50000, 'https://www.ocokorea.com//upload/images/product/233/233320/Product_1685196322691.jpg', 3),
    ('폴로 셔츠', '스마트 캐주얼 룩을 위한 칼라 있는 폴로 셔츠', 25000, 'https://img.danawa.com/prod_img/500000/590/023/img/13023590_1.jpg?_v=20201229125036', 1),
    ('치노 팬츠', '다양한 모임에 적합한 범용적인 치노 팬츠', 35000, 'https://risenbelow.cafe24.com/web/product/extra/small/202309/2d1cf0cd315067dc062659437e94b058.jpg', 2),
    ('부츠', '야외 활동을 위한 견고한 부츠', 70000, 'https://m.sovomall.co.kr/web/product/big/202301/486eddc06c9a0b6f066c61cae2565052.jpg', 3),
    ('후드티', '쌀쌀한 날씨에 따뜻하고 아늑한 후드티', 40000, 'https://image1.marpple.co/files/u_2005765/2023/8/original/b44d639e4fefddcf25a6c572107556e7ae4d633f1.png?q=92&w=1480&f=jpeg&bg=f6f6f6', 1),
    ('반바지', '여름 활동에 적합한 편안한 반바지', 28000, 'https://images.kolonmall.com/Prod_Img/10003414/2023/LM1/K1678936916671073NO01_LM1.jpg', 2),
    ('로퍼', '세련된 룩을 위한 우아한 로퍼', 60000, 'https://i.namu.wiki/i/TSJU8WcfbbVx5Gdmgu5mzqvlJnDmWfbNuZQAOhF5sbwABQk60Ffpha_nZdixSpc0t6Dh-FXGe6MW6qMTZA3i9A.webp', 3),
    ('스웨터', '부드럽고 세련된 스타일의 스웨터', 45000, 'https://simage.mujikorea.net/goods/00/06/76/93/W1AA026_COL_03_400.jpg', 1),
    ('조거 팬츠', '느긋한 스타일의 캐주얼 조거 팬츠', 32000, 'https://maninstore.co.kr/web/product/big/202309/3b5840cdb867eebe6c0433517c914d5c.jpg', 2),
    ('샌들', '해변을 즐기기 위한 가벼운 샌들', 25000, 'https://img.danawa.com/prod_img/500000/902/890/img/19890902_1.jpg?_v=20230425090714', 3),
    ('버튼업 셔츠', '정장적인 자리에 적합한 클래식한 버튼업 셔츠', 35000, 'https://img.29cm.co.kr/item/202309/11ee5f737b816827a5b32fb3508b9b8b.jpg?width=700', 1),
    ('카고 팬츠', '다수의 포켓이 있는 실용적인 카고 팬츠', 38000, 'https://m.vintagecrown.co.kr/web/product/big/10546.jpg', 2),
    ('옥스퍼드 슈즈', '우아한 옥스퍼드 슈즈', 65000, 'https://i.namu.wiki/i/U4pQHMnzpG0J_9dwYaFISrRLvxdw2IXA0bqoL45B1hnDKf5xTuzLk432eab2dI4OUWPFdXoRBiTDhV2pALwpeA.webp', 3),
    ('블라우스', '여성스러운 느낌을 주는 시크한 블라우스', 27000, 'https://images.vestiairecollective.com/images/resized/w=1246,q=75,f=auto,/produit/white-cotton-khaite-top-40257050-1_4.jpg', 1),
    ('치마', '다양한 스타일의 편안한 치마', 29000, 'https://img01.gosister.co.kr/goods/up_img/BOT-24853_S1.jpg', 2),
    ('하이힐', '세련된 룩을 위한 슬릭한 하이힐', 55000, 'https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/product/4276948516/B.jpg?164000000', 3),
    ('가디건', '다양한 계절에 활용 가능한 가디건', 42000, 'https://image.msscdn.net/images/goods_img/20210216/1795634/1795634_5_500.jpg', 1);

-- 가방
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('숄더백', '다양한 스타일에 어울리는 실용적인 숄더백', 60000, 'https://media-catalog.giglio.com/images/f_auto/t_prodZoom/v1/products/F04462.002_2/burberry.jpg', 5),
    ('백팩', '학교나 여행 시 필수품인 다용도 백팩', 45000, 'https://images.lululemon.com/is/image/lululemon/LM9APXS_0001_1?size=800,800', 5),
    ('크로스백', '가볍고 편안한 일상용 크로스백', 35000, 'https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/product/4700983140/B.jpg?746000000', 5),
    ('클러치백', '파티나 특별한 자리에 어울리는 우아한 클러치백', 50000, 'https://sitem.ssgcdn.com/01/61/37/item/1000308376101_i1_750.jpg', 5),
    ('토트백', '다용도로 사용 가능한 넓은 수납 공간을 제공하는 토트백', 55000, 'https://img.danawa.com/prod_img/500000/647/428/img/13428647_1.jpg?_v=20210811143257', 5),
    ('웨이스트 백', '스포티한 룩에 어울리는 유행하는 웨이스트 백', 40000, 'https://oasisproduct.cdn.ntruss.com/35131/detail/detail_35131_0_513b8bb0-df9d-4701-aac7-b903ebc894ed.jpg', 5),
    ('슬링백', '가볍고 스타일리시한 슬링백', 38000, 'https://qi-o.qoo10cdn.com/goods_image_big/0/1/4/1/7340670141_l.jpg', 5),
    ('클러치', '파티나 이벤트 시에 어울리는 간결한 디자인의 클러치', 32000, 'https://dealpang.hgodo.com//goods/22/10/43/1000024532/modify_main_073.jpg', 5),
    ('쇼퍼백', '많은 물건을 수납할 수 있는 넓은 공간을 가진 쇼퍼백', 70000, 'https://lh3.googleusercontent.com/proxy/CfnmUxukAS0Pt6S3JBqieALemu_QIFhtsobx_hEr90nBka0I7PVACN7K0fpmEMrcIQ2SXo2H81zpoZMPT7JK-_Tl8lmkf1izzDIi-Adtp4cb', 5),
    ('가방 액세서리', '가방을 더욱 멋지게 해주는 다양한 액세서리', 2500, 'https://ae01.alicdn.com/kf/Scc392c12ba2640deb899597f0281ed62s.jpg_640x640Q90.jpg_.webp', 5);

-- 아우터
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('코트', '겨울철 따뜻하게 입을 수 있는 스타일리시한 코트', 120000, 'https://m.bylegacy.co.kr/web/product/big/202111/5437a58a81fe0e813a504011a00fd89e.jpg', 6),
    ('자켓', '가을철에 적합한 다양한 스타일의 자켓', 80000, 'https://image.msscdn.net/images/goods_img/20240124/3818780/3818780_17062332050844_500.jpg', 6),
    ('패딩', '한겨울 따뜻하게 입을 수 있는 스타일리시한 패딩', 150000, 'https://sitem.ssgcdn.com/62/42/20/item/1000566204262_i1_750.jpg', 6),
    ('블루종', '캐주얼한 룩에 어울리는 스타일리시한 블루종', 90000, 'https://i.namu.wiki/i/0lMggklRZsYv4qoHzNEYH6OGGZlFt53laQGsUSqyLfBYuGhrVZmFPO8C-UgAum4MKdLumK0NjU3zxEqadzlcnw.webp', 6),
    ('가디건', '가을철에 입기 좋은 다양한 스타일의 가디건', 60000, 'https://image.msscdn.net/images/goods_img/20210216/1795634/1795634_5_500.jpg', 6),
    ('트렌치 코트', '센스 있는 룩을 연출할 수 있는 클래식한 트렌치 코트', 110000, 'https://i.namu.wiki/i/AII2cQ7VABaBdzWkcc09jwbwaXysdUpUEYAYr7n1YB6fd-AD5YKLE4gStmz9SWnyOVPGU4k_WI-oh6aPHZ6YqA.webp', 6),
    ('푸퍼 재킷', '겨울철 따뜻하게 입을 수 있는 스타일리시한 푸퍼 재킷', 130000, 'https://www.armani.com/variants/images/36594538430118184/E/w400.jpg', 6),
    ('블레이저', '정장 룩에 어울리는 클래식한 블레이저', 100000, 'https://m.marbete.co.kr/web/product/big/202110/3669a98bbb6f132e05caa4d9c91892af.jpg', 6),
    ('더플 코트', '추운 겨울을 따뜻하게 보낼 수 있는 스타일리시한 더플 코트', 140000, 'https://image.msscdn.net/images/goods_img/20160929/420779/420779_16965527205703_500.jpg', 6),
    ('레더 자켓', '센스 있는 룩을 연출할 수 있는 레더 자켓', 170000, 'https://image.msscdn.net/images/goods_img/20200909/1593755/1593755_1_500.jpg', 6);

-- 전자기기
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('스마트폰', '다양한 기능을 갖춘 최신형 스마트폰', 1200000, 'https://cdn.itdaily.kr/news/photo/202110/204882_205547_2622.jpg', 7),
    ('노트북', '성능이 우수한 다양한 용도에 적합한 노트북', 1500000, 'https://image.msscdn.net/images/goods_img/20220317/2425534/2425534_1_500.jpg', 7),
    ('태블릿', '이동 중에도 편리하게 사용할 수 있는 다양한 용도의 태블릿', 800000, 'https://img.hankyung.com/photo/202005/AKR20200528053700017_01_i.jpg', 7),
    ('스마트워치', '건강 관리와 생활 스타일을 지원하는 스마트워치', 500000, 'https://img4.yna.co.kr/etc/inner/KR/2018/09/13/AKR20180913053600017_03_i_P2.jpg', 7),
    ('무선 이어폰', '편리하고 풍부한 음향을 제공하는 무선 이어폰', 200000, 'https://sitem.ssgcdn.com/48/29/95/item/1000550952948_i1_750.jpg', 7),
    ('게이밍 노트북', '고성능으로 게이밍 및 다양한 업무용도로 활용할 수 있는 노트북', 2500000, 'https://imgs.kshop.co.kr/d2/product/emc/202107/A1/91/0743/A1910743_550_20210729171818.jpg', 7),
    ('스피커', '강력한 음향을 즐길 수 있는 다양한 용도의 스피커', 300000, 'https://1801889e95b1f9bf.kinxzone.com/webfile/product/1/1362/k7qsn1yy4nmc.jpg', 7),
    ('헤드폰', '편안하고 풍부한 음향을 즐길 수 있는 다양한 용도의 헤드폰', 180000, 'https://1801889e95b1f9bf.kinxzone.com/webfile/product/3/3917/48s763cr4wm0.jpeg', 7),
    ('드론', '촬영 및 놀이 용도로 사용할 수 있는 다양한 용도의 드론', 1000000, 'https://worldsky.kr/web/product/big/202007/0c5a643457efb971b6fec0a471becf16.jpg', 7),
    ('카메라', '고화질 사진을 찍을 수 있는 다양한 용도의 카메라', 5000000, 'https://image.kr.canon/pds/product/1667361588509_iOyLOcdGgQ.jpg', 7);

-- 신발
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('런닝화', '운동할 때 편안한 튼튼한 소재의 런닝화', 80000, 'https://images.kolonmall.com/Prod_Img/10003414/2021/LM1/K1679558126893077NO01_LM1.jpg', 3),
    ('샌들', '여름철 시원하고 가볍게 신을 수 있는 다양한 디자인의 샌들', 35000, 'https://www.montbell.co.kr/data/item/JBSXMUSD619/thumb-JBSXMUSD619_1000x1000_600x600.jpg', 3),
    ('로퍼', '세련된 룩을 연출할 수 있는 우아한 로퍼', 60000, 'https://simage.mujikorea.net/goods/31/10/76/60/G8SD508_COL_05_400.jpg', 3),
    ('부츠', '겨울철 따뜻하고 스타일리시한 부츠', 90000, 'https://m.daybin.co.kr/web/product/big/201910/f42d277ab26188371ef22c04b674cde6.jpg', 3),
    ('플랫 슈즈', '캐주얼한 룩에 어울리는 편안한 플랫 슈즈', 40000, 'https://simage.mujikorea.net/goods/31/12/19/60/G9SD609_COL_375_400.jpg', 3),
    ('힐 슈즈', '여성스러운 룩에 어울리는 우아한 힐 슈즈', 70000, 'https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/product/6314576228/B.jpg?155000000', 3),
    ('스니커즈', '다양한 캐주얼 룩에 어울리는 스타일리시한 스니커즈', 55000, 'https://img.danawa.com/prod_img/500000/037/639/img/13639037_1.jpg?_v=20220804140931', 3),
    ('슬리퍼', '가정에서 편안하게 신을 수 있는 슬리퍼', 25000, 'https://www.thessan.com/shopimages/thessancom/0040030000032.jpg?1529653790', 3),
    ('워커 슈즈', '근무 시 편안하게 신을 수 있는 스타일리시한 워커 슈즈', 75000, 'https://cdn.ownerclan.com/dTooutF0OoEQ7wGHWT0Q6D79_LtLtaADcpp77iRcrx8/marketize/auto/as/v1.jpg', 3),
    ('워커 부츠', '겨울철 따뜻하게 신을 수 있는 스타일리시한 워커 부츠', 100000, 'https://sitem.ssgcdn.com/34/72/57/item/1000287577234_i1_750.jpg', 3);

-- 액세서리
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('시계', '다양한 스타일의 패션을 연출할 수 있는 시계', 50000, 'https://m.dansoon.co.kr/web/product/big/202204/f27c2913d70450c81c170b3df04d5b6f.jpg', 4),
    ('목걸이', '다양한 스타일의 옷과 어울리는 목걸이', 30000, 'https://m.manbodang.com/web/product/big/202104/3d239715e85f88cfdaa8ef353011e29c.jpg', 4),
    ('귀걸이', '시크한 스타일의 다양한 디자인의 귀걸이', 25000, 'https://wingbling.co.kr/web/product/big/202205/ccfbc23dc8bbf31865d754bc447e55a0.jpg', 4),
    ('반지', '다양한 스타일의 옷과 어울리는 다양한 디자인의 반지', 35000, 'https://maadtr3966.cdn-nhncommerce.com/data/goods/23/03/11/1000005526/1000005526_detail_089.jpg', 4),
    ('팔찌', '여러 가지 스타일의 옷과 어울리는 다양한 디자인의 팔찌', 20000, 'https://www.costco.co.kr/medias/sys_master/images/he4/h22/46344189902878.jpg', 4),
    ('머플러', '겨울철 따뜻하고 스타일리시한 룩을 연출할 수 있는 머플러', 40000, 'https://img.29cm.co.kr/item/202311/11ee7dfa11bc685c8a7fe170e7b3e857.jpg?width=700', 4),
    ('모자', '다양한 계절과 스타일에 어울리는 다양한 디자인의 모자', 25000, 'https://thumbnail.10x10.co.kr/webimage/image/basic600/244/B002446586.jpg?cmd=thumb&w=400&h=400&fit=true&ws=false', 4),
    ('선글라스', '여름철 시원한 스타일을 연출할 수 있는 다양한 디자인의 선글라스', 45000, 'https://img.ssgdfs.com/upload/C00001/goos/org/481/240201000444481.jpg?RS=500x500&AR=0', 4),
    ('패션 마스크', '최신 패션 트렌드를 반영한 스타일리시한 패션 마스크', 15000, 'https://m.pbskorea.co.kr/web/product/big/202009/633081fda5b9e4041b78f85399a0da5c.jpg', 4),
    ('헤어 액세서리', '다양한 머리 스타일에 어울리는 다양한 디자인의 헤어 액세서리', 20000, 'https://www.bntnews.co.kr/data/bnt/image/201206/441d560357f6f87cd8312295c1f85d64.jpg', 4);
    
-- 가전제품
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('냉장고', '식품을 신선하게 보관할 수 있는 고급스러운 냉장고', 2000000, 'https://www.lge.co.kr/kr/images/refrigerators/md10039827/gallery/medium02.jpg', 8),
    ('세탁기', '효율적인 세탁 기능을 제공하는 고성능 세탁기', 1500000, 'https://www.costco.co.kr/medias/sys_master/images/hbd/h78/102204685713438.jpg', 8),
    ('에어컨', '시원한 바람을 제공하여 여름철을 시원하게 보내는 에어컨', 1800000, 'https://d21x3meyyr2jva.cloudfront.net/image_temp/1667192556000_%EC%97%90%EC%96%B4%EC%BB%A8_%EB%B2%BD%EA%B1%B8%EC%9D%B4.png', 8),
    ('TV', '고화질의 영상을 제공하는 다양한 기능을 갖춘 TV', 2500000, 'https://i.namu.wiki/i/RudfYNLERM6SRkSJdKFUaCrhDoLhdki2bMQ13vIVA1xdb8d8f0EEED3gNdnohebAO91Fre2LQPSxDRo27_t4xQ.webp', 8),
    ('청소기', '편리하고 강력한 성능을 제공하는 다기능 청소기', 300000, 'https://www.costco.co.kr/medias/sys_master/images/h63/h10/11960561762334.jpg', 8),
    ('오븐', '다양한 요리를 할 수 있는 고급스러운 오븐', 1000000, 'https://i.namu.wiki/i/kivsObYSVUzMeQnhei29tZRTpIoU3jQ_Nf9mOdLtCFwjc88BbBvO5JZGkNsAtBWGcX37h11xY-yWPmAcE3JI0w.webp', 8),
    ('건조기', '편리한 세탁을 위한 다기능 건조기', 1200000, 'https://i.namu.wiki/i/jzBnGvvTdrZbXf7y1Zhqoi2a531EC7ZA9Frl1_ECa33y-RhSGlgcIjBiL9EmUq9HszRwCSa2mEzcPcRmbPj00A.webp', 8),
    ('믹서기', '간편하고 효율적인 조리를 위한 고성능 믹서기', 500000, 'https://img.danawa.com/prod_img/500000/275/769/img/5769275_1.jpg?_v=20240319172302', 8),
    ('전자레인지', '다양한 요리를 간편하게 조리할 수 있는 전자레인지', 800000, 'https://i.namu.wiki/i/D0DCr00jDilaljWqIyMjZY205akwZlNG1VL5iYGqwUL14hnZvcYocwTXP4pXOloeXX2RoCynxVYx4Tbp06Xhig.webp', 8),
    ('커피머신', '고품질의 커피를 추출할 수 있는 고급스러운 커피머신', 700000, 'https://ecimg.cafe24img.com/pg154b77980609063/planitshop/web/product/big/20220719/588dbda15e9d55eba05d40a54f0e83d9.jpg', 8);

-- 서적
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('소설', '감동과 재미를 선사하는 다양한 장르의 소설', 25000, 'https://www.home-learn.co.kr/common/image.do?imgPath=newsroom&imgName=CK20230503160055517.jpg&imgGubun=D', 9),
    ('자기계발서', '자기계발 및 성장에 도움이 되는 다양한 주제의 서적', 30000, 'https://image.edaily.co.kr/images/Photo/files/NP/S/2021/04/PS21042900056.jpg', 9),
    ('역사서', '인류 역사와 문화에 대한 깊은 통찰을 제공하는 역사서', 35000, 'https://image.yes24.com/goods/102026284/XL', 9),
    ('과학서', '과학의 기본 개념부터 최신 연구 결과까지 다루는 과학서', 28000, 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/480D230655000.jpg', 9),
    ('만화', '다양한 장르와 스타일의 만화책', 20000, 'https://i.namu.wiki/i/qCrM0h9gsl2M0l3E34rDQgBtDUTic14T3eOTRRpxlifLuMr26g-TYxzqrBUPuGQne9A8kmsK3sXP-BN-OKhsBQ.webp', 9),
    ('여행지', '세계 각지의 아름다운 여행지를 소개하는 여행서', 40000, 'https://image.aladin.co.kr/product/17394/59/cover500/k132534700_1.jpg', 9),
    ('요리책', '다양한 요리 레시피와 조리 팁을 제공하는 요리책', 32000, 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791156161202.jpg', 9),
    ('경제서', '경제 이론과 현실에 대한 분석을 다루는 경제서', 27000, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9791193035054.jpg', 9),
    ('예술서', '미술, 음악, 연극 등 다양한 예술 분야에 대한 이해를 높이는 예술서', 35000, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9791159019210.jpg', 9),
    ('시', '명작 시편과 현대 시인의 작품을 수록한 시집', 23000, 'https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9788908060197.jpg', 9);

-- 가구
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('침대', '휴식을 위한 편안한 침대', 1000000, 'https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg', 10),
    ('소파', '가족과 함께 편안한 시간을 보낼 수 있는 소파', 800000, 'https://www.costco.co.kr/medias/sys_master/images/hb9/hfa/103710829936670.jpg', 10),
    ('식탁', '가족과 함께 식사를 할 수 있는 편안한 식탁', 600000, 'https://godomall.speedycdn.net/39e5bcd28b4b37cfe41ca29a47856cac/goods/1000000118/image/detail/_k_luter-2.jpg', 10),
    ('서랍장', '다양한 물건을 수납할 수 있는 실용적인 서랍장', 400000, 'https://img.danawa.com/prod_img/500000/211/811/img/3811211_1.jpg?_v=20230613141112', 10),
    ('의자', '다양한 용도로 활용할 수 있는 편안한 의자', 200000, 'https://www.westelm.co.kr/upload/goods/G100009156/G100009156_01_1_600.jpg', 10),
    ('화장대', '화장 및 미용을 위한 실용적인 화장대', 300000, 'https://m.domus.kr/web/product/big/20200419/5558ed64245bca00cf1187650342300f.jpg', 10),
    ('책상', '공부 및 업무를 위한 편안한 책상', 500000, 'https://www.costco.co.kr/medias/sys_master/images/h92/h8e/168530165104670.jpg', 10),
    ('옷장', '의류와 소품을 정리할 수 있는 넓은 수납 공간을 제공하는 옷장', 700000, 'https://ladytr8675.cdn-nhncommerce.com/data/goods/21/09/36//1000004813/1000004813_main_035.jpg', 10),
    ('선반', '다양한 소품이나 장식품을 진열할 수 있는 선반', 250000, 'https://cdn.imweb.me/thumbnail/20230607/0bced6d87ca50.jpg', 10),
    ('화장실 세트', '화장실을 꾸미고 정리하는 데 필요한 다양한 용품이 포함된 세트', 350000, 'https://jaru.kr/jaru/pd/ir/ttn-pink.PNG', 10);

-- 상의
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('셔츠', '다양한 스타일의 셔츠', 35000, 'https://item.elandrs.com/r/image/item/2023-07-05/1d66795a-a460-40a0-9019-5df1d6f88ec6.jpg?w=750&h=&q=100', 11),
    ('니트', '겨울철 따뜻하게 입을 수 있는 니트', 45000, 'https://m.ilsanghabo.com/web/product/big/202211/09daf073b398d5e1f5196cb7bd2a9ddf.jpg', 11),
    ('후드티', '캐주얼한 룩에 어울리는 후드티', 30000, 'https://image.msscdn.net/images/goods_img/20230223/3103007/3103007_16790367582310_500.jpg', 11),
    ('블라우스', '여성스러운 룩을 연출할 수 있는 블라우스', 40000, 'https://img.marieclairekorea.com/2021/06/mck_60c0507c6b206-562x563.jpg', 11),
    ('폴로 셔츠', '스마트 캐주얼 룩을 위한 폴로 셔츠', 38000, 'https://img.danawa.com/prod_img/500000/590/023/img/13023590_1.jpg?_v=20201229125036', 11),
    ('탑', '여름철 시원하게 입을 수 있는 탑', 25000, 'https://m.helenkaminski.co.kr/web/product/tiny/202403/543b3ef4edcb8082a6d5867f6cd1dc70.jpg', 11),
    ('점퍼', '가을철에 따뜻하게 입을 수 있는 점퍼', 55000, 'https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/product/5230998262/B.jpg?736000000', 11),
    ('카디건', '가을철에 입기 좋은 카디건', 40000, 'https://i.namu.wiki/i/T9v4k4F0WaJi0pEUma9_B22tW7c8ihdI6bOPXvygo462L1Azv_Zs0eCNdcbJfrLnFdES_ScJa5Lym74Is-irBA.webp', 11),
    ('조끼', '여름철에 시원하게 입을 수 있는 조끼', 30000, 'https://m.ziben24.com/web/product/medium/23FW-ZB-V2381.jpg', 11),
    ('티셔츠', '매일 입기 좋은 편안한 티셔츠', 20000, 'https://img.danawa.com/prod_img/500000/637/324/img/8324637_1.jpg?_v=20220308133438', 11);

-- 하의
INSERT INTO Products (ProductName, Description, Price, Photo, CategoryID) VALUES
    ('청바지', '다양한 스타일의 청바지', 60000, 'https://www.calvinklein.co.kr/dw/image/v2/BGLQ_PRD/on/demandware.static/-/Sites-ck-sea-master-catalog/default/dw8616ed36/images/CK/KR/C28_01_J3254021AA_FL-BT-F1.jpg?sw=548&sh=685&q=90', 12),
    ('슬랙스', '스마트한 룩에 어울리는 슬랙스', 45000, 'https://item.elandrs.com/upload/prd/orgimg/542/1909701542_0000001.JPG?w=750&h=&q=100', 12),
    ('반바지', '여름철에 시원하게 입을 수 있는 반바지', 35000, 'https://m.soupmall.co.kr/web/product/big/202308/d4e932759341ca20f88bd7fc3748766a.jpg', 12),
    ('스커트', '여성스러운 룩을 연출할 수 있는 스커트', 40000, 'https://image.msscdn.net/thumbnails/images/goods_img/20230824/3494293/3494293_17108233846982_big.jpg?w=390', 12),
    ('레깅스', '운동 및 캐주얼한 룩에 입기 좋은 레깅스', 25000, 'https://gdimg.gmarket.co.kr/2491241853/still/400?ver=1689056243', 12),
    ('트레이닝 팬츠', '운동할 때 편안하게 입을 수 있는 트레이닝 팬츠', 30000, 'https://gdimg.gmarket.co.kr/2482266550/still/400?ver=1655809034', 12),
    ('치노 팬츠', '다양한 스타일에 어울리는 치노 팬츠', 38000, 'https://risenbelow.cafe24.com/web/product/extra/small/202308/55c8d47aa7b76e9081ec761cd8a224b2.jpg', 12),
    ('롱 스커트', '여름철에 시원하게 입을 수 있는 롱 스커트', 50000, 'https://www.chloe.com/product_image/30280928IH/f/w1080.jpg', 12),
    ('점프수트', '스타일리시한 룩을 연출할 수 있는 점프수트', 70000, 'https://m.vinzip.kr/web/product/big//38/T8091.jpg', 12),
    ('레더 팬츠', '센스 있는 룩을 연출할 수 있는 레더 팬츠', 80000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwfKhpCg8UM9XGts4wDzgyX7pGYTMzBwCL9Q&usqp=CAU', 12);
