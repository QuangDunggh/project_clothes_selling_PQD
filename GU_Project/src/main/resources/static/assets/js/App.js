class App {
    static DOMAIN = location.origin;

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dwzktyh7a/image/upload";

    static BASE_SCALE_IMAGE = "c_limit,w_150,h_100,q_100";

    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                position: 'middle',
                icon: 'error',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static ShowConfirmSuspend(t) {
            return Swal.fire({
                position: 'middle',
                icon: 'warning',
                title: t,
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                reverseButtons: true
            });
        }
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.show({
                title: 'Success',
                message: t,
                position: 'topRight',
                color: 'green',
                timeout: 2500,
            });
        }

        static showErrorAlert(t) {
            iziToast.show({
                title: 'Error',
                message: t,
                position: 'topRight',
                color: 'red',
                timeout: 2500,
            });
        }
    }

}

class Product {
    constructor(id,title,category_id,category_name,subcategory_id,subcategory_name,
                description,fileName,fileFolder,fileUrl,cloudId) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.category_name = category_name;
        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
        this.description = description;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.cloudId = cloudId;
    }
}

class Category {
    constructor(id, category_name,quantity_product) {
        this.id = id;
        this.category_name = category_name;
        this.quantity_product = quantity_product
    }
}

class Subcategory {
    constructor(id, subcategoryName, category_id, categoryName) {
        this.id = id;
        this.subcategoryName = subcategoryName;
        this.category_id = category_id;
        this.categoryName = categoryName
    }
}

class ProductsCombination{
    constructor(id,product_id,product_title,product_combination_id,
                combination_string,sku,fileName,fileFolder,fileUrl,
                cloudId,total_stock,available,sold,defective,unit_price,maximum_retail_price) {
        this.id = id;
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_combination_id = product_combination_id;
        this.combination_string = combination_string;
        this.sku = sku;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.cloudId = cloudId;
        this.total_stock = total_stock;
        this.available = available;
        this.sold = sold;
        this.defective = defective;
        this.unit_price = unit_price;
        this.maximum_retail_price = maximum_retail_price;
    }
}

class Color{
    constructor(id, color_name) {
        this.id = id;
        this.color_name = color_name
    }
}

class Size{
    constructor(id, size_name) {
        this.id = id;
        this.size_name = size_name
    }
}