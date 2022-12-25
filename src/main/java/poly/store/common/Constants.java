
package poly.store.common;

/**
 * Class chua thong tin constant
 * 
 * @author khoa-ph
 * @version 1.00
 */
public class Constants {

	/* List man hinh user */
	public static final String USER_DISPLAY_LOAD = "user/layout/load";
	// Duong dan den man hinh trang chu nguoi dung
	public static final String USER_DISPLAY_INDEX = "user/home/index";
	// Duong dan den man hinh login nguoi dung
	public static final String USER_DISPLAY_LOGIN = "user/security/login";
	// Duong dan den man hinh register
	public static final String USER_DISPLAY_REGISTER = "user/security/register";
	// Duong dan den man hinh confirm code
	public static final String USER_DISPLAY_CONFIRM_CODE = "user/security/confirm-code";
	// Duong dan den man hinh success
	public static final String USER_DISPLAY_ALERT_STATUS = "user/security/success";
	// Duong dan den man hinh forget password
	public static final String USER_DISPLAY_FORGET_PASSWORD = "user/security/forget-password";
	// Duong dan den man hinh reset password
	public static final String USER_DISPLAY_RESET_PASSWORD = "user/security/reset-password";
	// Duong dan den man hinh 404 page
	public static final String USER_DISPLAY_404_PAGE = "user/security/404page";
	// Duong dan den man hinh danh sach san pham
	public static final String USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY = "user/list/list-by-category";
	// Duong dan den man hinh chi tiet san pham
	public static final String USER_DISPLAY_DETAIL_PRODUCT = "user/detail/product_detail";
	// Duong dan den man hinh gio hang
	public static final String USER_DISPLAY_SHOPPING_CART = "user/cart/shopping_cart";
	// Duong dan den man hinh thanh toan
	public static final String USER_DISPLAY_CHECKOUT = "user/checkout/checkout_form";
	// Duong dan den man hinh thanh toan thanh cong
	public static final String USER_DISPLAY_CHECKOUT_SUCCESS = "user/checkout/success";
	// Duong dan den man hinh tai khoan
	public static final String USER_DISPLAY_ACCOUNT_PAGE = "user/account/account_index";
	// Duong dan den man hinh thong tin tai khoan
	public static final String USER_DISPLAY_ACCOUNT_INFORMATION = "user/account/account_information";
	// Duong dan den man hinh thay doi mat khau
	public static final String USER_DISPLAY_ACCOUNT_CHANGE_PASSWORD = "user/account/account_change_password";
	// Duong dan den man hinh dia chi
	public static final String USER_DISPLAY_ACCOUNT_ADDRESS = "user/account/account_address";
	// Duong dan den man hinh them dia chi
	public static final String USER_DISPLAY_ACCOUNT_ADDRESS_ADD = "user/account/account_address_add";
	// Duong dan den man hinh them dia chi
	public static final String USER_DISPLAY_ACCOUNT_ADDRESS_UPDATE = "user/account/account_address_update";
	// Duong dan den man hinh yeu thich
	public static final String USER_DISPLAY_ACCOUNT_FAVORITE = "user/account/account_favorite";
	// Duong dan den man hinh lien he
	public static final String USER_DISPLAY_CONTACT = "user/contact/contact_form";
	// Duong dan den man hinh lich su don hang
	public static final String USER_DISPLAY_ACCOUNT_ORDER = "user/account/account_order";
	// Duong dan den man hinh hoa don chi tiet
	public static final String USER_DISPLAY_ACCOUNT_INVOICE = "user/account/account_invoice";

	// Duong dan den man hinh blog
	public static final String USER_DISPLAY_BLOGS = "user/blog/blog_form";
	// Duong dan den man hinh blog chi tiet
	public static final String USER_DISPLAY_BLOGS_DETAILS = "user/blog/blog_detail_form";
	// Duong dan den man hinh blog chi tiet
	public static final String USER_DISPLAY_SEARCH_DETAIL = "user/ketqua/search_form";

	public static final String USER_DISPLAY_DISCOUNT = "user/cart/voucher";

	/* List man hinh admin */
	// Duong dan den man hinh trang chu quan tri
	public static final String USER_DISPLAY_ADMIN_INDEX = "admin/dashboard/index";
	// Duong dan den man hinh trang quan ly danh sach nguoi dung
	public static final String USER_DISPLAY_ADMIN_USER_LIST = "admin/users/list";
	// Duong dan den man hinh trang quan ly danh sach nhan vien
	public static final String USER_DISPLAY_ADMIN_EMPLOYEE_LIST = "admin/employees/list";
	// Duong dan den man hinh trang quan ly thong tin nhan vien
	public static final String USER_DISPLAY_ADMIN_EMPLOYEE_FORM = "admin/employees/form";
	// Duong dan den man hinh trang quan ly danh sach nhan vien
	public static final String USER_DISPLAY_ADMIN_CATEGORY_LIST = "admin/categories/list";
	// Duong dan den man hinh trang quan ly thong tin danh muc
	public static final String USER_DISPLAY_ADMIN_CATEGORY_FORM = "admin/categories/form";
	// Duong dan den man hinh trang quan ly danh sach nhan vien
	public static final String USER_DISPLAY_ADMIN_MANUFACTURER_LIST = "admin/manufactures/list";
	// Duong dan den man hinh trang quan ly thong tin danh muc
	public static final String USER_DISPLAY_ADMIN_MANUFACTURER_FORM = "admin/manufactures/form";
	// Duong dan den man hinh trang quan ly thong tin menu bac 1
	public static final String USER_DISPLAY_ADMIN_NAV_FORM = "admin/menu/form";
	// Duong dan den man hinh trang quan ly danh sach menu bac 1
	public static final String USER_DISPLAY_ADMIN_NAV_LIST = "admin/menu/list";
	// Duong dan den man hinh trang quan ly thong tin menu bac 2
	public static final String USER_DISPLAY_ADMIN_NAV2_FORM = "admin/menu2/form";
	// Duong dan den man hinh trang quan ly danh sach menu bac 2
	public static final String USER_DISPLAY_ADMIN_NAV2_LIST = "admin/menu2/list";
	// Duong dan den man hinh trang quan ly thong tin cua hang
	public static final String USER_DISPLAY_ADMIN_SHOP_FORM = "admin/informationShop/form";
	// Duong dan den man hinh trang quan ly danh sach thong tin cua hang
	public static final String USER_DISPLAY_ADMIN_SHOP_LIST = "admin/informationShop/list";
	// Duong dan den man hinh trang quan ly danh sach nhan vien
	public static final String USER_DISPLAY_ADMIN_PRODUCT_LIST = "admin/products/list";
	// Duong dan den man hinh trang quan ly thong tin nhan vien
	public static final String USER_DISPLAY_ADMIN_PRODUCT_FORM = "admin/products/form";
	// Duong dan den man hinh trang tao thong so
	public static final String USER_DISPLAY_ADMIN_EXTEND_SPECIFICATION = "admin/extend/create-specification";
	// Duong dan den man hinh trang chinh sua hinh anh
	public static final String USER_DISPLAY_ADMIN_EXTEND_CROP_IMAGE = "admin/extend/crop-image";
	// Duong dan den man hinh trang quan ly danh sach nhan vien
	public static final String USER_DISPLAY_ADMIN_DISCOUNT_LIST = "admin/discount/list";
	// Duong dan den man hinh trang quan ly thong tin nhan vien
	public static final String USER_DISPLAY_ADMIN_DISCOUNT_FORM = "admin/discount/form";
	// Duong dan den man hinh trang quan ly binh luan
	public static final String USER_DISPLAY_ADMIN_COMMENT_PENDING = "admin/comment/list_pending";
	// Duong dan den man hinh trang quan ly binh luan
	public static final String USER_DISPLAY_ADMIN_COMMENT_APPROVED = "admin/comment/list_approved";
	// Duong dan den man hinh trang don hang dang cho xy ly
	public static final String USER_DISPLAY_ADMIN_ORDER_PENDING = "admin/order/order_pending";
	// Duong dan den man hinh trang don hang dang cho giao
	public static final String USER_DISPLAY_ADMIN_ORDER_SHIPPING = "admin/order/order_shipping";
	// Duong dan den man hinh trang don hang thanh cong
	public static final String USER_DISPLAY_ADMIN_ORDER_SUCCESS = "admin/order/order_success";
	// Duong dan den man hinh trang don hang bi huy
	public static final String USER_DISPLAY_ADMIN_ORDER_CANCEL = "admin/order/order_cancel";
	// Duong dan den man hinh trang thong ke san pham ban chay theo ngay
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_PRODUCT_DAY = "admin/statisticalProduct/top_product";
	// Duong dan den man hinh trang thong ke doanh thu
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_REVENUE = "admin/statisticalRevenue/list";
	// Duong dan den man hinh trang thong ke don hang
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_ORDER = "admin/statisticalOrder/list";

	// Duong dan den man hinh trang quan ly danh sach blog
	public static final String USER_DISPLAY_ADMIN_BLOG_LIST = "admin/blog/list";
	// Duong dan den man hinh trang quan ly thong tin blog
	public static final String USER_DISPLAY_ADMIN_BLOG_FORM = "admin/blog/form";

}
