import { useState } from 'react';
import { s_logo } from '../../assets';
import { navLinks } from './constants';

const Header = () => {

    const [active, setActive] = useState("Home");
    const [toggle, setToggle] = useState(false);
    return (
        <nav className="w-full flex py-6 justify-between items-center navbar">
            <div className="flex items-center">
                <img src={s_logo} alt="logo" className="w-[100px] h-[100px]" />
                <div className="flex flex-col ml-4">
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Crypto</span>
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Trader</span>
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Helper</span>
                </div>
            </div>
            <ul className="list-none sm:flex hidden justify-end items-center flex-1">
                {navLinks.map((nav, index) => (
                    <li
                        key={nav.id}
                        className={`font-poppins font-normal cursor-pointer text-[22px] ${active === nav.title ? "text-white" : "text-dimWhite"
                            } ${index === navLinks.length - 1 ? "mr-0" : "mr-10"}`}
                        onClick={() => setActive(nav.title)}
                    >
                        <a href={`#${nav.id}`}>{nav.title}</a>
                    </li>
                ))}
            </ul>


        </nav>
    );
}

export default Header;