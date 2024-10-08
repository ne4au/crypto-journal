import { FC, RefObject } from 'react';
import { s_logo } from '../../assets';
import SigningType from '../sign/SigningType';
import { useTranslation } from 'react-i18next';
import LocalizationDropdown from './LocalizationDropdown';

interface Props<E extends HTMLElement> {
    signingOpenListener: (signingType: SigningType) => void,
    singingInRef: RefObject<E>,
    singingUpRef: RefObject<E>,
}

const Header: FC<Props<HTMLLIElement>> = ({
    signingOpenListener,
    singingInRef,
    singingUpRef,
}) => {
    const { t, i18n } = useTranslation();
    console.log(i18n.language);
 
    return (
        <nav className="w-full flex py-6 px-1 justify-between items-center navbar">
            <div className="flex items-center">
                <img src={s_logo} alt="logo" className="w-[100px] h-[100px]" />
                <div className="flex flex-col ml-4">
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Crypto</span>
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Trader</span>
                    <span className="font-poppins font-normal text-[22px] text-dimWhite">Helper</span>
                </div>
            </div>
            <ul className="list-none sm:flex hidden justify-end items-center flex-1">
                <li key='localization'
                    className={`font-poppins font-normal cursor-pointer text-[22px] text-dimWhite mr-10`}
                >
                    <LocalizationDropdown />
                </li>

                <li key='home'
                    className={`font-poppins font-normal cursor-pointer text-[22px] text-dimWhite mr-10`}>
                    <button className='hover:scale-105 active:scale-110'>
                        <a href={`#home`} className='hover:underline underline-offset-4 '>
                            {t('header.home')}
                        </a>
                    </button>
                </li>

                <li key='about_us'
                    className={`font-poppins font-normal cursor-pointer text-[22px] text-dimWhite mr-10`}>
                    <button className='hover:scale-105 active:scale-110'>
                        <a href={`#about_us`} className='hover:underline underline-offset-4 '>
                            {t('header.about')}
                        </a>
                    </button>
                </li>

                <li key='sign_in'
                    ref={singingUpRef}
                    className={`font-poppins font-normal cursor-pointer text-[22px] text-dimWhite mr-10`}>
                    <button type='button' className='hover:underline underline-offset-4 hover:scale-105 active:scale-110'
                        onClick={() => signingOpenListener(SigningType.SIGNING_IN)}>
                        {t('header.sign_in')}
                    </button>
                </li>

                <li key='sign_up'
                    ref={singingInRef}
                    className={`font-poppins font-normal cursor-pointer text-[22px] text-dimWhite mr-10`}>
                    <button type='button' className='hover:underline underline-offset-4 hover:scale-105 active:scale-110'
                        onClick={() => signingOpenListener(SigningType.SIGNING_UP)}>
                        {t('header.sign_up')}
                    </button>
                </li>
            </ul>


        </nav>
    );
}

export default Header;