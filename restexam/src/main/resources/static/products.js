// 모든 상품 불러오기
// 모든 상품 불러오기 (수정된 버전)
function fetchProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(data => {
            const list = document.getElementById('productList');
            list.innerHTML = ''; // 기존 리스트 초기화

            data.forEach(product => {
                const item = document.createElement('li');

                // 1. 상품 텍스트 정보 생성
                const textSpan = document.createElement('span');
                textSpan.textContent = `Name: ${product.name}, Price: ${product.price} `;
                item.appendChild(textSpan);

                // 2. 수정 버튼 (Edit Button) 생성
                const editButton = document.createElement('button');
                editButton.textContent = 'Edit';

                // 3. 수정 버튼에 'click' 이벤트 리스너 추가
                // 이 방식은 product.name에 따옴표가 있어도 전혀 문제없습니다.
                editButton.addEventListener('click', () => {
                    editProduct(product.id, product.name, product.price);
                });
                item.appendChild(editButton);

                // 4. 삭제 버튼 (Delete Button) 생성
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';

                // 5. 삭제 버튼에 'click' 이벤트 리스너 추가
                deleteButton.addEventListener('click', () => {
                    deleteProduct(product.id);
                });
                item.appendChild(deleteButton);

                // 6. 완성된 li 항목을 ul 리스트에 추가
                list.appendChild(item);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}


// 상품 추가하기 (프론트엔드 검증 추가)
function addProduct() {
    const name = document.getElementById('productName').value.trim();
    const price = document.getElementById('productPrice').value.trim();

    // 입력값 검증
    if (!name || !price || isNaN(price) || parseFloat(price) <= 0) {
        alert("상품명과 가격을 올바르게 입력하세요!");
        return;
    }

    fetch('/api/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name, price: parseFloat(price) })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("상품 추가 실패");
            }
            return response.json();
        })
        .then(() => {
            fetchProducts(); // 상품 목록 새로고침
            document.getElementById('productName').value = ''; // 입력 필드 초기화
            document.getElementById('productPrice').value = '';
        })
        .catch(error => console.error('Error adding product:', error));
}


// 상품 수정하기
function editProduct(id, oldName, oldPrice) {
    const newName = prompt("Enter new name:", oldName);
    const newPrice = prompt("Enter new price:", oldPrice);

    // 프롬프트 입력값 검증 (취소 누르지 않았고, 빈 값이 아닐 때)
    if (newName !== null && newPrice !== null && newName.trim() !== '' && newPrice.trim() !== '') {

        // 가격이 유효한 숫자인지 추가 검증
        const priceValue = parseFloat(newPrice);
        if (isNaN(priceValue) || priceValue <= 0) {
            alert("유효한 가격을 입력하세요!");
            return;
        }

        fetch(`/api/products/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: newName, price: priceValue })
        })
            .then(response => {
                // addProduct에서처럼 .ok 확인 (서버가 200-299 상태 코드를 반환했는지)
                if (!response.ok) {
                    throw new Error("상품 수정 실패");
                }
                // .json() 호출을 제거하거나, 서버가 수정한 객체를 반환하는 경우에만 사용
                // 여기서는 목록을 새로고침할 것이므로 .json()이 필요 없습니다.
            })
            .then(() => fetchProducts()) // 상품 목록 새로고침
            .catch(error => console.error('Error updating product:', error));
    }
}

// 상품 삭제하기
function deleteProduct(id) {
    if (confirm("Are you sure you want to delete this product?")) {
        fetch(`/api/products/${id}`, { method: 'DELETE' })
            .then(() => fetchProducts()) // 상품 목록 새로고침
            .catch(error => console.error('Error deleting product:', error));
    }
}

// 페이지 로드 시 모든 상품 불러오기
document.addEventListener('DOMContentLoaded', function () {
    fetchProducts();
});



//function fetchProducts() {
//    fetch('/api/products')
//        .then(response => response.json())
//        .then(data => {
//            const list = document.getElementById('productList');
//            list.innerHTML = '';
//            data.forEach(product => {
//                const item = document.createElement('li');
//                item.textContent = `Name: ${product.name}, Price: ${product.price}`;
//                list.appendChild(item);
//            });
//        });
//}
//function addProduct() {
//    const name = document.getElementById('productName').value;
//    const price = document.getElementById('productPrice').value;
//
//    fetch('/api/products', {
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify({ name: name, price: price })
//    }).then(() => {
//        fetchProducts();  // Refresh the list
//        document.getElementById('productName').value = '';  // Clear the input
//        document.getElementById('productPrice').value = '';  // Clear the input
//    });
//}
//
//document.addEventListener('DOMContentLoaded', function () {
//    fetchProducts();
// });