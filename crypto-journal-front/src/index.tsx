import { StrictMode } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { createBrowserRouter, RouterProvider, } from "react-router-dom";
import { Provider } from 'react-redux';
import store from './fragments/store';
import reportWebVitals from './reportWebVitals';
import Home from "./home/components/Home";
import './i18n/config';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
]);


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <Provider store={store}>
    <StrictMode>
      <RouterProvider router={router} />
    </StrictMode>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
