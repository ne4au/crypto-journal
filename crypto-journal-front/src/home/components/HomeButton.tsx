import { FC } from 'react';

interface HomeButtonProps {
    styles: string,
}

const HomeButton : FC<HomeButtonProps> = ({ 
    styles
 }) => (

  <button type="button" className={`py-4 px-6 font-poppins font-medium text-[18px]
  text-primary bg-blue-gradient rounded-[10px] outline-none ${styles} hover:scale-105
  active:translate-y-[4px]`}>
    Get Started
  </button>
);

export default HomeButton;
