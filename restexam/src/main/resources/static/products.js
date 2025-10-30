// 모든 상품 불러오기
function fetchProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(data => {
            const list = document.getElementById('productList');
            list.innerHTML = ''; // 기존 리스트 초기화
            data.forEach(product => {
                const item = document.createElement('li');
                item.innerHTML = `Name: ${product.name}, Price: ${product.price}
                                  <button onclick="editProduct(${product.id}, '${product.name}', ${product.price})">Edit</button>
                                  <button onclick="deleteProduct(${product.id})">Delete</button>`;
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

    if (newName !== null && newPrice !== null) {
        fetch(`/api/products/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: newName, price: parseFloat(newPrice) })
        })
            .then(response => response.json())
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
//});