let productService;

class ProductService {

    search()
    {
        const url = `${config.baseUrl}/products`;

        axios.get(url)
             .then(response => {
                 console.log(response.data)
                 let data = {};
                 data.products = response.data;

                 templateBuilder.build('product', data, 'content');
             })
             .catch(error => {
                 console.log(error);
             })
    }

}





document.addEventListener('DOMContentLoaded', () => {
    productService = new ProductService();
    productService.search();
});
