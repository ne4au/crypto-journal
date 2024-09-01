import { FC } from 'react';
import styles from '../../styles';
import { arrowUp } from '../../assets';
import SigningType from '../sign/SigningType';


interface Props {
    signingOpenListener: (signingType: SigningType) => void,
}

const GetStarted: FC<Props> = ({
    signingOpenListener,
}) => (
    <button className={`${styles.flexCenter} w-[140px] h-[140px] rounded-full bg-blue-gradient p-[2px] hover:scale-105 
    before:ease relative overflow-hidden border shadow-2xl transition-all 
    before:absolute before:right-0 before:top-0 before:h-[140px] before:w-6 before:translate-x-12 before:rotate-6
    before:bg-white before:opacity-10 before:duration-700 hover:shadow-sky-300 hover:before:-translate-x-40
    active:translate-y-[4px] active:shadow-sky-400`}
        onClick={() => signingOpenListener(SigningType.SIGNING_UP)}>
        <div className={`${styles.flexCenter} flex-col bg-primary w-[100%] h-[100%] rounded-full`}>
            <div className={`${styles.flexStart} flex-row`}>
                <p className="font-poppins font-medium text-[18px] leading-[23.4px]">
                    <span className="text-gradient">Get</span>
                </p>
                <img src={arrowUp} alt="arrow-up" className="w-[23px] h-[23px] object-contain" />
            </div>

            <p className="font-poppins font-medium text-[18px] leading-[23.4px]">
                <span className="text-gradient">Started</span>
            </p>

        </div>

    </button>
)

export default GetStarted;