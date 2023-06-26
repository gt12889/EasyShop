let cartService;

class ShoppingCartService {

    cart = {};

    addToCart(productId)
    {
        const url = `${config.baseUrl}/cart/products/${productId}`;
        const headers = userService.getHeaders();

        axios.post(url, {} ,{headers})
            .then(response => {
                this.cart = response.data;

                this.updateCartDisplay()

            })
            .catch(error => {

                const data = {
                    error: "Add to cart failed."
                };

                templateBuilder.append("error", data, "errors")
            })
    }

    loadCart()
    {

        const url = `${config.baseUrl}/cart`;
        const headers = userService.getHeaders();

        axios.get(url)
            .then(response => {
                this.cart = response.data;

                this.updateCartDisplay()

            })
            .catch(error => {

                const data = {
                    error: "Load cart failed."
                };

                templateBuilder.append("error", data, "errors")
            })

    }

    updateCartDisplay()
    {
        try {
            const itemCount = Object.keys(this.cart.items).length;
            const cartControl = document.getElementById("cart-items")

            cartControl.innerText = itemCount;
        }
        catch (e) {
            
        }
    }
}





document.addEventListener('DOMContentLoaded', () => {
    cartService = new ShoppingCartService();

    if(userService.isLoggedIn())
    {
        cartService.loadCart();
    }

});
