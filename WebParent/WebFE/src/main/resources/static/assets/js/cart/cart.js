const quantityFields = document.querySelectorAll('.item-quantity');

// Add a change event listener to each input field to update the cart total
quantityFields.forEach((field) => {
    field.addEventListener('change', updateCartTotal);
});

// Function to update the cart total whenever an item quantity changes
function updateCartTotal() {
    let cartTotal = 0;
    const cartItems = document.querySelectorAll('.cart-item');
    cartItems.forEach((item) => {
        const itemPrice = parseFloat(item.querySelector('.item-price').textContent.replace('$', ''));
        const itemQuantity = parseInt(item.querySelector('.item-quantity').value);
        cartTotal += itemPrice * itemQuantity;
    });
    document.querySelector('#cart-total').textContent = '$' + cartTotal.toFixed(2);
}