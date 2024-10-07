import { HttpInterceptorFn } from '@angular/common/http';

export const myInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('JWT'); // Token'ı localStorage'dan al

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}` // Token'ı header'a ekle
      }
    });
    return next(cloned); // Token'lı isteği gönder
  } else {
    return next(req); // Token yoksa normal isteği gönder
  }
};
